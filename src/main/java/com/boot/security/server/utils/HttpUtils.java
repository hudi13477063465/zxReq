package com.boot.security.server.utils;
 
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**   
 * http工具类,每次请求都会重新建立连接
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: http utils
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:
 * </p> 
 * 
 * @author LIJUNJIE 
 * @version 1.0
 */
public class HttpUtils {

	private static final String URL_PARAM_CONNECT_FLAG = "&";
	private static final int SIZE = 1024 * 1024;
	private static Log log = LogFactory.getLog(HttpUtils.class);
	
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST     = "POST";
    private static final int BUFFER_SIZE = 1024;
    
    public static  final String webClient = "web"; 
    public static  final String androidClient = "android";
    public static  final String iosClient = "ios";

	private HttpUtils() {
	}

	/**
	 * GET METHOD
	 * 
	 * @param strUrl
	 *            String
	 * @param map
	 *            Map
	 * @throws IOException
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static String URLGet(String strUrl, Map map) throws Exception {
		if(strUrl != null && !"".equals(strUrl)){
			log.info(String.format("------请求的URL：%s",strUrl));
		}
		if(map !=null && !map.isEmpty()){
			log.info(String.format("------请求的参数map:%s",(map)));
		}
		String strTotalURL = "";
		StringBuffer result=new StringBuffer();
		String params=getUrl(map);
		if (strUrl.indexOf("?") == -1) {
			strTotalURL = StringUtils.isBlank(params)?strUrl:(strUrl+"?"+params);
		} else {
			strTotalURL = StringUtils.isBlank(params)?strUrl:(strUrl+"&"+params);
		}
		log.debug("Get Url:" + strTotalURL);
		HttpURLConnection con = null;
		InputStream is = null;
		BufferedReader in = null;
		try {
			URL url = new URL(strTotalURL);
			con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setFollowRedirects(true);
			con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
            con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
			is = con.getInputStream();
			in = new BufferedReader(new InputStreamReader(is,"UTF-8"), SIZE);
			while (true) {
				String line = in.readLine();
				if (line == null) {
					break;
				} else {
					result.append(line);
				}
			}
		} catch (Exception e){
			log.error("------http请求发生异常",e);
			throw e;
		} finally {
			try {
				if (in!=null) {
					in.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			}
			try {
				if (is!=null) {
					is.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			} 
			try {
				if (con!=null) {
					con.disconnect();
				}
			} catch (Exception e) {
				log.error("------关闭流发生异常",e);
			}
		}
		
		return result.toString();
	}

	/**
	 * POST METHOD
	 * 
	 * @param strUrl
	 *            String
	 * @param content
	 *            Map
	 * @throws IOException
	 * @return List
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String URLPost(String strUrl, Map map) throws Exception {
		if(strUrl != null && !"".equals(strUrl)){
			log.info(String.format("------请求的URL：%s",strUrl));
		}
		if(map !=null && !map.isEmpty()){
			log.info(String.format("------请求的参数map:%s",(map)));
		}
		String params = "";
		params = getUrl(map);
		String totalURL = null;
		if (strUrl.indexOf("?") == -1) {
			totalURL = strUrl + (StringUtils.isBlank(params)?"":"?" + params);
		} else {
			totalURL = strUrl + (StringUtils.isBlank(params)?"":"&" + params);
		}
		log.debug("Post Url:" + totalURL);
		HttpURLConnection con = null;
		InputStream is = null;
		BufferedReader bin = null;
		StringBuffer sbuffer = null;
		try {
			URL url = new URL(strUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
			con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			con.getOutputStream().write(params.getBytes());
			con.getOutputStream().flush();
			con.getOutputStream().close();
			is = con.getInputStream();
			bin = new BufferedReader(new InputStreamReader(is,"UTF-8"), SIZE);
			sbuffer = new StringBuffer();
			while (true) {
				String line = bin.readLine();
				if (line == null) {
					break;
				} else {
					sbuffer.append(line);
				}
			}
		} catch (Exception e){
			log.error("------http请求发生异常",e);
			throw e;
		} finally {
			try {
				if (bin!=null) {
					bin.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			} 
			try {
				if (is!=null) {
					is.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			} 
			try {
				if (con!=null) {
					con.disconnect();
				}
			} catch (Exception e) {
				log.error("------关闭流发生异常",e);
			}
		}
		return sbuffer.toString();
	}

	
	

	/**
	 * POST METHOD
	 * 
	 * @param strUrl
	 *            String
	 * @param content
	 *            params
	 * @throws IOException
	 */
	public static String jsonPost(String strUrl, String params) throws Exception {
		if(strUrl != null && !"".equals(strUrl)){
			log.info(String.format("------请求的URL：%s",strUrl));
		}
		if(params != null && !"".equals(params)){
			log.info(String.format("------请求的参数parpms:%s",params));
		}
		HttpURLConnection con = null;
		InputStream is = null;
		BufferedReader bin = null;
		StringBuffer sbuffer = null;
		try {
			URL url = new URL(strUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
			con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			con.getOutputStream().write(params.getBytes("utf-8"));
			con.getOutputStream().flush();
			con.getOutputStream().close();
			is = con.getInputStream();
			bin = new BufferedReader(new InputStreamReader(is,"UTF-8"), SIZE);
			sbuffer = new StringBuffer();
			while (true) {
				String line = bin.readLine();
				if (line == null) {
					break;
				} else {
					sbuffer.append(line);
				}
			}
		} catch (Exception e){
			log.error("------http请求发生异常",e);
			throw e;
		} finally {
			try {
				if (bin!=null) {
					bin.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			} 
			try {
				if (is!=null) {
					is.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			} 
			try {
				if (con!=null) {
					con.disconnect();
				}
			} catch (Exception e) {
				log.error("------关闭流发生异常",e);
			}
				
			
		}
		return sbuffer.toString();
	}
	
	
	/**
	 * POST METHOD
	 * 
	 * @param strUrl
	 *            String
	 * @param content
	 *            params
	 * @throws IOException
	 */
	public static String jsonPost(String strUrl, String params,boolean encode) throws Exception {
		if(strUrl != null && !"".equals(strUrl)){
			log.info(String.format("------请求的URL：%s",strUrl));
		}
		if(params != null && !"".equals(params)){
			log.info(String.format("------请求的参数params：%s", params));
		}
		if(encode)
		{
			HttpURLConnection con = null;
			InputStream is = null;
			BufferedReader bin = null;
			StringBuffer sbuffer = null;
			try {
				URL url = new URL(strUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setAllowUserInteraction(false);
				con.setUseCaches(false);
				con.setRequestMethod("POST");
				con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
				con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
				con.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
				con.getOutputStream().write(URLEncoder.encode(params,"utf-8").getBytes());
				con.getOutputStream().flush();
				con.getOutputStream().close();
				is = con.getInputStream();
				bin = new BufferedReader(new InputStreamReader(is,"UTF-8"), SIZE);
				sbuffer = new StringBuffer();
				while (true) {
					String line = bin.readLine();
					if (line == null) {
						break;
					} else {
						sbuffer.append(line);
					}
				}
			} catch (Exception e) {
				log.error("------http请求发生异常",e);
				throw e;
			}  finally {
				try {
					if (bin!=null) {
						bin.close();
					}
				} catch (IOException e) {
					log.error("------关闭流发生异常",e);
				}
				try {
					if (is!=null) {
						is.close();
					}
				} catch (IOException e) {
					log.error("------关闭流发生异常",e);
				} 
				try {
					if (con!=null) {
						con.disconnect();
					}
				} catch (Exception e) {
					log.error("------关闭流发生异常",e);
				}
			}
			return sbuffer.toString();
		}else
		{
			return jsonPost(strUrl,params);
		}
	}
	
	
	/**
	 * 
	 * @param map
	 *            Map
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	private static String getUrl(Map map) {
		if(map !=null && !map.isEmpty()){
			log.info(String.format("------请求的参数map:%s", (map)));
		}
		if (null == map || map.keySet().size() == 0) {
			return "";
		}
		StringBuffer url = new StringBuffer();
		Set keys = map.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();) {
			String key = String.valueOf(i.next());
			if (map.containsKey(key)) {
				Object val = map.get(key);
				String str = val != null ? val.toString() : "";
				try {
					str = URLEncoder.encode(str, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					log.error("------获取路径 error：",e);
					e.printStackTrace();
				}
				//System.out.print(key+":"+val+"\t");
				url.append(key).append("=").append(str).append(
						URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = "";
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(""
				+ strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}
		return (strURL);
	}
	
	public static String jsonPost2Scan(String strUrl, String params) throws Exception {
		if(strUrl != null && !"".equals(strUrl)){
			log.info(String.format("------请求的URL：%s", strUrl));
		}
		if(params != null && !"".equals(params)){
			log.info(String.format("------请求的参数params：%s", params));
		}
		HttpURLConnection con = null;
		InputStream is = null;
		BufferedReader bin = null;
		StringBuffer sbuffer = null;
		try {
			URL url = new URL(strUrl);
			con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setAllowUserInteraction(false);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
			con.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
			con.setRequestProperty("Content-Type","application/json;charset=utf-8");
			con.getOutputStream().write(params.getBytes("utf-8"));
			con.getOutputStream().flush();
			con.getOutputStream().close();
			is = con.getInputStream();
			bin = new BufferedReader(new InputStreamReader(is,"UTF-8"), SIZE);
			sbuffer = new StringBuffer();
			while (true) {
				String line = bin.readLine();
				if (line == null) {
					break;
				} else {
					sbuffer.append(line);
				}
			}
		}  catch (Exception e){
			log.error("------http请求发生异常",e);
			throw e;
		} finally {
			try {
				if (bin!=null) {
					bin.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			} 
			try {
				if (is!=null) {
					is.close();
				}
			} catch (IOException e) {
				log.error("------关闭流发生异常",e);
			}
			try {
				if (con!=null) {
					con.disconnect();
				}
			} catch (Exception e) {
				log.error("------关闭流发生异常",e);
			}
		}
		return sbuffer.toString();
	}

	 /**
     * 执行下载文件请求。
     * 
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param paramCharset 参数编码
     * @param filename 存储路径（全文件名）
     * @throws IOException
     */
    public static File   downloadJdPayBill(String strUrl, Map<String, String> params,String paramCharset,String filename) throws Exception {
        if(strUrl != null && !"".equals(strUrl)){
            log.info(String.format("------请求的URL：%s",strUrl));
        }
        if(params !=null && !params.isEmpty()){
            log.info(String.format("------请求的参数map:%s",(params)));
        }
        String paramsStr = buildReqLineParam(params,paramCharset);
        log.info("download Url:" + strUrl+",paramsStr:"+paramsStr);
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader bf = null;
        StringBuffer sbuffer = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setAllowUserInteraction(false);
            conn.setUseCaches(false);
            conn.setRequestMethod(METHOD_POST);
            conn.setConnectTimeout(30000);//设置连接主机超时 三十秒等待时间
            conn.setReadTimeout(30000);//设置从主机读取数据超时 三十秒等待时间
            conn.getOutputStream().write(paramsStr.getBytes(paramCharset));
            conn.getOutputStream().flush();
            
           // log.info("POST " + conn.getURL() + " " + conn.getHeaderField(0));
          //  log.info("Content-Length:" + conn.getHeaderField("Content-Length"));
            String returnCode = conn.getHeaderField("Return-Code");
            // returnCode==null也当是可以下载
            returnCode = (returnCode==null?"0000":returnCode);
           // log.info("Return-Code:" + returnCode); 
            log.info(String.format("downloadJdPayBill , url:%s,getHeaderField 0:%s,Content-Length:%s,Return-Code:%s", conn.getURL(),conn.getHeaderField(0),conn.getHeaderField("Content-Length"),returnCode));
            if(!"0000".equals(returnCode)){
                String errMsg = "京东支付对账文件下载失败";
                if("00111".equals(returnCode)){
                    errMsg = "商户未授权";
                }
                if("00124".equals(returnCode)){
                    errMsg = "请求文件无效";
                }
                if("00112".equals(returnCode)){
                    errMsg = "商户IP未授权";
                }
                throw new RuntimeException(errMsg);
            }
            // 保存下载文件
            return saveFile(conn,filename);
        } catch (Exception e){
            log.error("------http请求发生异常",e);
            throw e;
        } finally {
            try {
                if (bf!=null) {
                    bf.close();
                }
            } catch (IOException e) {
                log.error("------关闭流发生异常",e);
            } 
            try {
                if (is!=null) {
                    is.close();
                }
            } catch (IOException e) {
                log.error("------关闭流发生异常",e);
            } 
            try {
                if (conn!=null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                log.error("------关闭流发生异常",e);
            }
        }
    }
    
    /**
     * 下载文件
     * @param conn
     * @param filename 保存的文件全名
     * @throws IOException void
     */
    private static File  saveFile(HttpURLConnection conn,String filename) throws IOException{
        byte[] buf = new byte[BUFFER_SIZE];  
        int size = 0;  
  
        //建立文件  
        File file = new File(filename);  
        if(!file.exists()) {  
            if(!file.getParentFile().exists()) {  
                file.getParentFile().mkdirs();  
            }  
            file.createNewFile();  
        }  

        //文件输出流
        FileOutputStream fos = new FileOutputStream(filename);
        
        //获取网络输入流  
        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
        
        //保存文件  
        while ((size = bis.read(buf)) != -1){  
            fos.write(buf, 0, size);  
        }
        try {
            fos.close();
        } catch (Exception e) {
        	log.error("------下载文件输出流关闭 error：",e);
            e.printStackTrace();
        }  
        try {
            bis.close();
        } catch (Exception e) {
        	log.error("------下载文件输入流关闭 error：",e);
            e.printStackTrace();
        } 
        return file;
    }
    
    

    /**
     * 生成request-line风格的请求参数
     * @param map
     * @param paramCharset
     * @return String
     */
    private static String buildReqLineParam(Map map,String paramCharset) {
        if(map !=null && !map.isEmpty()){
            log.info(String.format("------请求的参数map:%s",(map)));
        }
        if (null == map || map.keySet().size() == 0) {
            return "";
        }
        StringBuffer url = new StringBuffer();
        Set keys = map.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = String.valueOf(i.next());
            if (map.containsKey(key)) {
                Object val = map.get(key);
                String str = val != null ? val.toString() : "";
                try {
                    str = URLEncoder.encode(str, paramCharset);
                } catch (UnsupportedEncodingException e) {
                	log.error("------生成request-line风格的请求参数  error：",e);
                    e.printStackTrace();
                }
                url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
            }
        }
        String strURL = "";
        strURL = url.toString();
        if (URL_PARAM_CONNECT_FLAG.equals(""
                + strURL.charAt(strURL.length() - 1))) {
            strURL = strURL.substring(0, strURL.length() - 1);
        }
        log.info("buildReqLineParam,result:"+strURL);
        return strURL;
    }
    

    /**
     * 判断请求的客户端类型 web、android、ios
     * @param request
     * @return
     */
    public static String getClientTypeDetail(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        if(request == null){
            return "";
        }
        if(StringUtils.isEmpty(userAgent)){
            return webClient; // 默认webClient
        }else{
            userAgent = userAgent.toLowerCase();
            if ( userAgent != null &&(userAgent.toLowerCase().indexOf(androidClient) != -1)){
                return androidClient;
            }
            
            if ( userAgent != null &&(userAgent.toLowerCase().indexOf(iosClient) != -1)){
                return iosClient;
            }
            
            return webClient;//默认webClient
        }
        
    }
}
