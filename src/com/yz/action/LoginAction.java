package com.yz.action;

import com.yz.auth.AuthObject;
import com.yz.model.*;
import com.yz.service.*;
import com.yz.util.DateTimeKit;
import com.yz.util.MD5Util;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction{

	// 登陆
	private String username;
	private String password;
	//环境变量
	@Resource(name = "authObject")
	private AuthObject authObject;

	// service层对象
	@Resource
	private UnitService unitService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private PnoticeService pnoticeService;
	@Resource
	private SuccessexampleService successexampleService;
	@Resource
	private TroubleshootingService troubleshootingService;

	// list对象
	private List<UserRole> userRoles;
	private List<Unit> units;
	private List<Pnotice> pnotices;
	private List<Successexample> successexamples;
	private List<Troubleshooting> troubleshootings;

	// 用户身份证号码
	private String cardid;


	/** ***************************** 报文公共部分 *************************** */
	/**
	 * 报文根结点
	 */
	private final String MSG_ROOT = "message";

	/**
	 * 报文头结点
	 */
	private final String MSG_HEAD = "head";

	/**
	 * 报文体结点
	 */
	private final String MSG_BODY = "body";

	/**
	 * 服务版本号
	 */
	private final String MSG_VSERSION = "version";

	/**
	 * 服务版本值
	 */
	private final String MSG_VSERSION_VALUE = "1.0";

	/**
	 * 服务类型
	 */
	private final String MSG_SERVICE_TYPE = "serviceType";

	/**
	 * 服务类型值
	 */
	private final String MSG_SERVICE_TYPE_VALUE = "AuthenService";

	/**
	 * 报文体 认证方式
	 */
	private final String MSG_AUTH_MODE = "authMode";

	/**
	 * 报文体 证书认证方式
	 */
	private final String MSG_AUTH_MODE_CERT_VALUE = "cert";

	/**
	 * 报文体 口令认证方式
	 */
	private final String MSG_AUTH_MODE_PASSWORD_VALUE = "password";

	/**
	 * 报文体 属性集
	 */
	private final String MSG_ATTRIBUTES = "attributes";

	/**
	 * 报文体 属性
	 */
	private final String MSG_ATTRIBUTE = "attr";

	/**
	 * 报文体 属性名
	 */
	private final String MSG_NAME = "name";

	/**
	 * 报文父级节点
	 */
	// --hegd
	public static final String MSG_PARENT_NAME = "parentName";

	/**
	 * 报文体 属性空间
	 */
	private final String MSG_NAMESPACE = "namespace";
	/** ****************************************************************** */

	/** ***************************** 请求报文 *************************** */
	/**
	 * 报文体 应用ID
	 */
	private final String MSG_APPID = "appId";

	/**
	 * 访问控制
	 */
	private final String MSG_ACCESS_CONTROL = "accessControl";

	private final String MSG_ACCESS_CONTROL_TRUE = "true";

	private final String MSG_ACCESS_CONTROL_FALSE = "false";

	/**
	 * 报文体 认证结点
	 */
	private final String MSG_AUTH = "authen";

	/**
	 * 报文体 认证凭据
	 */
	private final String MSG_AUTHCREDENTIAL = "authCredential";

	/**
	 * 报文体 客户端结点
	 */
	private final String MSG_CLIENT_INFO = "clientInfo";

	/**
	 * 报文体 公钥证书
	 */
	private final String MSG_CERT_INFO = "certInfo";

	/**
	 * 报文体 客户端结点
	 */
	private final String MSG_CLIENT_IP = "clientIP";

	/**
	 * 报文体 detach认证请求包
	 */
	private final String MSG_DETACH = "detach";

	/**
	 * 报文体 原文
	 */
	private final String MSG_ORIGINAL = "original";

	/**
	 * 报文体 用户名
	 */
	private final String MSG_USERNAME = "username";

	/**
	 * 报文体 口令
	 */
	private final String MSG_PASSWORD = "password";

	/**
	 * 报文体 属性类型
	 */
	private final String MSG_ATTRIBUTE_TYPE = "attributeType";

	/**
	 * 指定属性 portion
	 */
	private final String MSG_ATTRIBUTE_TYPE_PORTION = "portion";

	/**
	 * 指定属性 all
	 */
	private final String MSG_ATTRIBUTE_TYPE_ALL = "all";
	/**
	 * 指定属性列表控制项 attrType
	 */
	private final String MSG_ATTR_TYPE = "attrType";
	/** ****************************************************************** */

	/** ***************************** 响应报文 *************************** */
	/**
	 * 报文体 认证结果集状态
	 */
	private final String MSG_MESSAGE_STATE = "messageState";

	/**
	 * 响应报文消息码
	 */
	private final String MSG_MESSAGE_CODE = "messageCode";

	/**
	 * 响应报文消息描述
	 */
	private final String MSG_MESSAGE_DESC = "messageDesc";

	/**
	 * 报文体 认证结果集
	 */
	private final String MSG_AUTH_RESULT_SET = "authResultSet";

	/**
	 * 报文体 认证结果
	 */
	private final String MSG_AUTH_RESULT = "authResult";

	/**
	 * 报文体 认证结果状态
	 */
	private final String MSG_SUCCESS = "success";

	/**
	 * 报文体 认证错误码
	 */
	private final String MSG_AUTH_MESSSAGE_CODE = "authMessageCode";

	/**
	 * 报文体 认证错误描述
	 */
	private final String MSG_AUTH_MESSSAGE_DESC = "authMessageDesc";
	/** ****************************************************************** */

	/** ************************** 业务处理常量 *************************** */
	/**
	 * 认证地址
	 */
	private final String KEY_AUTHURL = "authURL";

	/**
	 * 应用标识
	 */
	private final String KEY_APP_ID = "appId";

	/**
	 * 认证方式
	 */
	private final String KEY_CERT_AUTHEN = "certAuthen";

	/**
	 * session中原文
	 */
	private final String KEY_ORIGINAL_DATA = "original_data";

	/**
	 * 客户端返回的认证原文，request中原文
	 */
	private final String KEY_ORIGINAL_JSP = "original_jsp";

	/**
	 * 证书认证请求包
	 */
	private final String KEY_SIGNED_DATA = "signed_data";

	/**
	 * 证书
	 */
	private final String KEY_CERT_CONTENT = "certInfo";

	/** ****************************************************************** */

	/**
	 * 用户登陆
	 *
	 * @throws Exception
	 */
	public String login() throws Exception {

		if(isLogined()||isVailSSOCertificate())
		{
			return "loginSucc";
		}

		if (checkDatebase())// 检查数据库
		{
			initCurrentUserRole();
			return "loginSucc";
		}


		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {

			setLoginFailMsg();
			return "adminLogin";
		}
		password = MD5Util.convertMD5(MD5Util.string2MD5(password));
		UserRole userRoleLogin = userRoleService.getUserRoleByUsernameAndPassword(username,
				password);
		if (userRoleLogin == null) {

			setLoginFailMsg();

			return "adminLogin";
		} else {
			setCurrentUserRoleInSession(userRoleLogin);
			return "loginSucc";
		}
	}


	public String toLogin() {

		if(isLogined()||isVailSSOCertificate())
		{
			return "loginSucc";
		}

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String appId = authObject.getAppId();
		String authURL = authObject.getAuthURL();
		String attrType = authObject.getAttrType();
		String attributes = authObject.getAttributes();

		boolean isCompeted = isNotBlankString(appId) || isNotBlankString(attrType)
				|| isNotBlankString(authURL) || isNotBlankString(attributes);

		if (!isCompeted) {
			System.out.println("从配置文件中获得应用标识，网关地址，属性列表控制项，认证方式发生异常");
		} else {
			session.put("appId", appId);
			session.put("authURL", authURL);
			session.put("attrType", attrType);
			session.put("attributes", attributes);
		}

		String randNum = generateRandomNum();
		if (!isNotBlankString(randNum)) {
			System.out.println("证书认证数据不完整！");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		/***********************************************************************
		 * 第三步 服务端返回认证原文 *
		 **********************************************************************/
		// 设置认证原文到session，用于程序向后传递，通讯报文中使用
		session.put("original_data", randNum);

		// 设置认证原文到页面，给页面程序提供参数，用于产生认证请求数据包
		request.put("original", randNum);

		// 产生认证原文
		return "adminLogin";

	}

	private boolean isLogined() {

		if(session.get(currentUserRole)!=null)
		{
			return true;
		}
		return false;
	}

	private boolean isVailSSOCertificate() {

		if(checkSSOUser(getSSOUserAndSetSSOUserIntoHttpSession()))
		{
			return  true;
		}
		return  false;
	}

	private UserRole getSSOUserAndSetSSOUserIntoHttpSession() {
		String usersfz = (String) session.get("usersfz");
		if(StringUtils.isNotBlank(usersfz))
		{
			UserRole userRoleLogin = userRoleService.getUserRoleByCardid(usersfz);
			if(userRoleLogin!=null)
			{
				setCurrentUserRoleInSession(userRoleLogin);
				return userRoleLogin;
			}
		}
		return new UserRole();
	}

	private boolean checkSSOUser(UserRole userRole)
	{
		if(userRole.getId()!=0)
		{
			return true;
		}
		return  false;
	}


	public String certificateLogin() {
		// 第四步：客户端认证
		// 第五步：服务端验证认证原文
		// 第六步：应用服务端认证
		// 第七步：网关返回认证响应
		// 第八步：服务端处理
		// 获取应用标识及网关认证地址

		String cardid = "";// 获取身份证

		boolean isSuccess = true;
		String errCode = null, errDesc = null;

		// 可以根据需求使用不同的获取方法
		String appId = authObject.getAppId();
		String authURL = authObject.getAuthURL();
		String attrType = authObject.getAttrType();
		String attributes = authObject.getAttributes();

		boolean isCompeted = isNotBlankString(appId) || isNotBlankString(attrType)
				|| isNotBlankString(authURL) || isNotBlankString(attributes);

		if (!isCompeted) {
			isSuccess = false;
			errDesc = "应用标识或网关认证地址不可为空";
			System.out.println("应用标识或网关认证地址不可为空\n");
		}
		String original_data = null, signed_data = null, original_jsp = null, username = null, password = null;
		// 获取认证数据信息
		if (isSuccess) {
			System.out.println("应用标识及网关的认证地址读取成功！\n应用标识：" + appId + "\n认证地址："
					+ authURL + "\n");

			if (isNotBlankString((String) session.get(KEY_ORIGINAL_DATA))
					&& isNotBlankString((String) req.getParameter(KEY_SIGNED_DATA))
					&& isNotBlankString((String) req.getParameter(KEY_ORIGINAL_JSP))) {
				// 获取session中的认证原文
				original_data = (String) session.get(KEY_ORIGINAL_DATA);
				// 获取request中的认证原文
				original_jsp = (String) req.getParameter(KEY_ORIGINAL_JSP);

				// ****************第五步：服务端验证认证原文
				if (!original_data.equalsIgnoreCase(original_jsp)) {
					isSuccess = false;
					errDesc = "客户端提供的认证原文与服务端的不一致";
					System.out.println("客户端提供的认证原文与服务端的不一致！\n");

				} else {
					// 获取证书认证请求包
					signed_data = (String) req.getParameter(KEY_SIGNED_DATA);

					// 随机密钥
					original_data = new BASE64Encoder().encode(original_jsp
							.getBytes());
					System.out.println("读取认证原文和认证请求包成功！\n认证原文：" + original_jsp
							+ "\n认证请求包：" + signed_data + "\n");
				}

			} else {
				isSuccess = false;
				errDesc = "证书认证数据不完整";
				System.out.println("证书认证数据不完整！\n");
			}

		}

		// **************************第六步：应用服务端认证 *
		// 认证处理
		try {
			byte[] messagexml = null;
			if (isSuccess) {

				// *** 1 组装认证请求报文数据 ** 开始 **//*
				Document reqDocument = DocumentHelper.createDocument();
				Element root = reqDocument.addElement(MSG_ROOT);
				Element requestHeadElement = root.addElement(MSG_HEAD);
				Element requestBodyElement = root.addElement(MSG_BODY);
				// 组装报文头信息
				requestHeadElement.addElement(MSG_VSERSION).setText(
						MSG_VSERSION_VALUE);
				requestHeadElement.addElement(MSG_SERVICE_TYPE).setText(
						MSG_SERVICE_TYPE_VALUE);

				// 组装报文体信息

				// 组装客户端信息
				Element clientInfoElement = requestBodyElement
						.addElement(MSG_CLIENT_INFO);

				Element clientIPElement = clientInfoElement
						.addElement(MSG_CLIENT_IP);

				clientIPElement.setText(req.getRemoteAddr());

				// 组装应用标识信息
				requestBodyElement.addElement(MSG_APPID).setText(appId);

				Element authenElement = requestBodyElement.addElement(MSG_AUTH);

				Element authCredentialElement = authenElement
						.addElement(MSG_AUTHCREDENTIAL);

				// 组装证书认证信息
				authCredentialElement.addAttribute(MSG_AUTH_MODE,
						MSG_AUTH_MODE_CERT_VALUE);

				authCredentialElement.addElement(MSG_DETACH).setText(
						signed_data);
				authCredentialElement.addElement(MSG_ORIGINAL).setText(
						original_data);

				// 支持X509证书 认证方式
				// 获取到的证书
				// javax.security.cert.X509Certificate x509Certificate = null;
				// certInfo 为base64编码证书
				// 可以使用
				// "certInfo =new
				// BASE64Encoder().encode(x509Certificate.getEncoded());"
				// 进行编码
				// authCredentialElement.addElement(MSG_CERT_INFO).setText(certInfo);

				requestBodyElement.addElement(MSG_ACCESS_CONTROL).setText(
						MSG_ACCESS_CONTROL_FALSE);

				// 组装口令认证信息
				// username = request.getParameter( "" );//获取认证页面传递过来的用户名/口令
				// password = request.getParameter( "" );
				// authCredentialElement.addAttribute(MSG_AUTH_MODE,MSG_AUTH_MODE_PASSWORD_VALUE
				// );
				// authCredentialElement.addElement( MSG_USERNAME
				// ).setText(username);
				// authCredentialElement.addElement( MSG_PASSWORD
				// ).setText(password);

				// 组装属性查询列表信息
				Element attributesElement = requestBodyElement
						.addElement(MSG_ATTRIBUTES);
				if (attrType == null || attrType.equals("")) {
					errDesc = "属性列表控制项为空，请确认配置！！！";
					throw new ServletException("属性列表控制项为空，请确认配置！！！");
				}
				attributesElement.addAttribute(MSG_ATTRIBUTE_TYPE, attrType);
				if (attrType.equals("portion")) {
					attributes = (String) session.get(MSG_ATTRIBUTES);
					if (null != attributes && !"".equals(attributes)) {
						String[] attrs = attributes.split(";");
						int num = attrs.length;
						for (int i = 0; i < num; i++) {
							String att = attrs[i];
							if (att.contains("X509") || att.contains("_saml")) {
								// 证书属性
								addAttribute(attributesElement, att,
										"http://www.jit.com.cn/cinas/ias/ns/saml/saml11/X.509");
							} else {
								// pms 属性 或 ums 属性
								addAttribute(attributesElement, att,
										"http://www.jit.com.cn/ums/ns/user");
								addAttribute(attributesElement, att,
										"http://www.jit.com.cn/pmi/pms/ns/role");
								addAttribute(attributesElement, att,
										"http://www.jit.com.cn/pmi/pms/ns/privilege");
							}
						}
					}

				}
				// TODO 取公共信息
				// addAttribute(attributesElement, "X509Certificate.SubjectDN",
				// "http://www.jit.com.cn/cinas/ias/ns/saml/saml11/X.509");
				// addAttribute(attributesElement, "UMS.UserID",
				// "http://www.jit.com.cn/ums/ns/user");
				// addAttribute(attributesElement, "机构字典",
				// "http://www.jit.com.cn/ums/ns/user");

				// *** 1 组装认证请求报文数据 ** 完毕 **//*

				StringBuffer reqMessageData = new StringBuffer();
				try {
					// *** 2 将认证请求报文写入输出流 ** 开始 **//*
					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					XMLWriter writer = new XMLWriter(outStream);
					writer.write(reqDocument);
					messagexml = outStream.toByteArray();
					// *** 2 将认证请求报文写入输出流 ** 完毕 **//*

					reqMessageData.append("请求内容开始！\n");
					reqMessageData.append(outStream.toString() + "\n");
					reqMessageData.append("请求内容结束！\n");
					System.out.println(reqMessageData.toString() + "\n");
				} catch (Exception e) {
					isSuccess = false;
					errDesc = "组装请求时出现异常";
					System.out.println("组装请求时出现异常");
				}
			}

			// ******************* 创建与网关的HTTP连接，发送认证请求报文，并接收认证响应报文***********
			// *** 1 创建与网关的HTTP连接 ** 开始 **//*
			int statusCode = 500;
			HttpClient httpClient = null;
			PostMethod postMethod = null;
			if (isSuccess) {
				// HTTPClient对象
				httpClient = new HttpClient();
				postMethod = new PostMethod(authURL);

				// 设置报文传送的编码格式
				postMethod.setRequestHeader("Content-Type",
						"text/xml;charset=UTF-8");
				// *** 2 设置发送认证请求内容 ** 开始 **//*
				postMethod.setRequestBody(new ByteArrayInputStream(messagexml));
				// *** 2 设置发送认证请求内容 ** 结束 **//*
				// 执行postMethod
				try {
					// *** 3 发送通讯报文与网关通讯 ** 开始 **//*
					statusCode = httpClient.executeMethod(postMethod);
					// *** 3 发送通讯报文与网关通讯 ** 结束 **//*
				} catch (Exception e) {
					isSuccess = false;
					errCode = String.valueOf(statusCode);
					errDesc = e.getMessage();
					System.out.println("与网关连接出现异常\n");
				}
			}
			// 第七步：网关返回认证响应
			StringBuffer respMessageData = new StringBuffer();
			String respMessageXml = null;
			if (isSuccess) {
				// 当返回200或500状态时处理业务逻辑
				if (statusCode == 200 || statusCode == 500) {
					// 从头中取出转向的地址
					try {
						// *** 4 接收通讯报文并处理 ** 开始 **//*
						byte[] inputstr = postMethod.getResponseBody();

						ByteArrayInputStream ByteinputStream = new ByteArrayInputStream(
								inputstr);
						ByteArrayOutputStream outStream = new ByteArrayOutputStream();
						int ch = 0;
						try {
							while ((ch = ByteinputStream.read()) != -1) {
								int upperCh = (char) ch;
								outStream.write(upperCh);
							}
						} catch (Exception e) {
							isSuccess = false;
							errDesc = e.getMessage();
						}

						if (isSuccess) {
							// 200 表示返回处理成功
							if (statusCode == 200) {
								respMessageData.append("响应内容开始！\n");
								respMessageData.append(new String(outStream
										.toByteArray(), "UTF-8")
										+ "\n");
								respMessageData.append("响应内容开始！\n");
								respMessageXml = new String(outStream
										.toByteArray(), "UTF-8");
							} else {
								// 500 表示返回失败，发生异常
								respMessageData.append("响应500内容开始！\n");
								respMessageData.append(new String(outStream
										.toByteArray())
										+ "\n");
								respMessageData.append("响应500内容结束！\n");
								isSuccess = false;
								errCode = String.valueOf(statusCode);
								errDesc = new String(outStream.toByteArray());
							}
							System.out.println(respMessageData.toString()
									+ "\n");
						}
						// *** 4 接收通讯报文并处理 ** 结束 **//*
					} catch (IOException e) {
						isSuccess = false;
						errCode = String.valueOf(statusCode);
						errDesc = e.getMessage();
						System.out.println("读取认证响应报文出现异常！");
					}
				}
			}

			// *** 1 创建与网关的HTTP连接 ** 结束 **//*

			// ****************第八步：服务端处理 *
			Document respDocument = null;
			Element headElement = null;
			Element bodyElement = null;
			if (isSuccess) {
				respDocument = DocumentHelper.parseText(respMessageXml);

				headElement = respDocument.getRootElement().element(MSG_HEAD);
				bodyElement = respDocument.getRootElement().element(MSG_BODY);

				// *** 1 解析报文头 ** 开始 **//*
				if (headElement != null) {
					boolean state = Boolean.valueOf(
							headElement.elementTextTrim(MSG_MESSAGE_STATE))
							.booleanValue();
					if (state) {
						isSuccess = false;
						errCode = headElement.elementTextTrim(MSG_MESSAGE_CODE);
						errDesc = headElement.elementTextTrim(MSG_MESSAGE_DESC);
						System.out.println("认证业务处理失败！\t" + errDesc + "\n");
					}
				}
			}

			if (isSuccess) {
				System.out.println("解析报文头成功！\n");
				// 解析报文体
				// 解析认证结果集
				Element authResult = bodyElement.element(MSG_AUTH_RESULT_SET)
						.element(MSG_AUTH_RESULT);

				isSuccess = Boolean.valueOf(
						authResult.attributeValue(MSG_SUCCESS)).booleanValue();
				if (!isSuccess) {
					errCode = authResult
							.elementTextTrim(MSG_AUTH_MESSSAGE_CODE);
					errDesc = authResult
							.elementTextTrim(MSG_AUTH_MESSSAGE_DESC);
					System.out.println("身份认证失败，失败原因：" + errDesc);
				}
			}

			if (isSuccess) {
				System.out.println("身份认证成功！\n");
				String ss = bodyElement.elementTextTrim("accessControlResult");

				System.out.println(ss);

				// 解析用户属性列表
				Element attrsElement = bodyElement.element(MSG_ATTRIBUTES);
				if (attrsElement != null) {
					List<Element> namespacesElements = attrsElement
							.elements(MSG_ATTRIBUTE);
					if (namespacesElements != null
							&& namespacesElements.size() > 0) {
						System.out.println("属性个数：" + namespacesElements.size());
						for (int i = 0; i < namespacesElements.size(); i++) {

							String arr = namespacesElements.get(i)
									.attributeValue(MSG_NAME);

							if (arr.equals("loginname")) {
								String text = namespacesElements.get(i)
										.getText();
								int beginIndex = text.indexOf(" ") + 1;
								int endIndex = text.indexOf(",");
								cardid = text.substring(beginIndex, endIndex);// 获取身份证
								System.out.println("身份证号：" + cardid);
							}
						}

					}
				} else {
					String loginfail = "证书无效";
					request.put("loginFail", loginfail);
					return "adminLogin";
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			errDesc = e.getMessage();
		}
		if (!isSuccess) {
			if (isNotBlankString(errCode)) {
				request.put("errCode", errCode);
			}
			if (isNotBlankString(errDesc)) {
				request.put("errDesc", errDesc);
			}
			String loginfail = errDesc;
			request.put("loginFail", loginfail);
			return "adminLogin";
		} else {
			// 登陆
			UserRole userRoleLogin = userRoleService.getUserRoleByCardid(cardid);

			if (userRoleLogin == null) {
				String loginfail = "证书已失效";
				request.put("loginFail", loginfail);
				return "adminLogin";
			} else {
				setCurrentUserRoleInSession(userRoleLogin);
				return "loginSucc";
			}
		}
	}


	public String welcome() {

		// 欢迎界面
		pnotices = pnoticeService.getPnotices();
		successexamples = successexampleService.getSuccessexamples();// 所有
		troubleshootings = troubleshootingService.getTroubleshootings();// 所有


		return "welcome";
	}


	/*
	 * 获取IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 用户注销
	 *
	 * @throws IOException
	 */
	public String logout() throws IOException {
		session.clear();
		response.sendRedirect("blank.jsp");
		return null;
	}



	private void setLoginFailMsg() {
		request.put("loginFail", "用户名或密码不能为空");
	}


	private void initCurrentUserRole() throws Exception {

		UserRole userRoleTest = new UserRole();
		userRoleTest.setNumber("测试人员");
		userRoleTest.setUsername("test");

		userRoleTest.setPassword(MD5Util.convertMD5(MD5Util
				.string2MD5("test")));

		userRoleTest.setUserLimit(1);
		userRoleService.add(userRoleTest);

		session.put("currentUserRole", userRoleTest);
	}


	private void setCurrentUserRoleInSession(UserRole currentUserRole) {

		// 设置登陆时间
		if (session.get("currentUserRole") == null) {
			setLoginTime(currentUserRole);
			currentUserRole.setClues(null);
			currentUserRole.setInjurycases(null);
			currentUserRole.setPersons(null);
			currentUserRole.setPnotices(null);
			session.put("currentUserRole", currentUserRole);
		}
	}

	/*
	 * 产生认证原文
	 */
	private String generateRandomNum() {
		String num = "1234567890abcdefghijklmnopqrstopqrstuvwxyz";
		int size = 6;
		char[] charArray = num.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb
					.append(charArray[((int) (Math.random() * 10000) % charArray.length)]);
		}
		return sb.toString();
	}


	// 设置登陆时间
	private void setLoginTime(UserRole userRoleLogin) {
		if (userRoleLogin.getBeforeLoginTime() == ""
				|| userRoleLogin.getBeforeLoginTime() == null) {
			userRoleLogin.setBeforeLoginTime(DateTimeKit.getLocalTime());
		} else {
			userRoleLogin.setBeforeLoginTime(userRoleLogin
					.getCurrentLoginTime());
		}
		userRoleLogin.setCurrentLoginTime(DateTimeKit.getLocalTime());
		userRoleService.update(userRoleLogin);
	}

	private boolean checkDatebase() {
		units = unitService.getUnits();
		userRoles = userRoleService.getUserRoles();
		if (units.size() == 0 && userRoles.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 向xml插入结点
	 */
	private void addAttribute(Element attributesElement, String name,
							  String namespace) {
		Element attr = attributesElement.addElement(MSG_ATTRIBUTE);
		attr.addAttribute(MSG_NAME, name);
		attr.addAttribute(MSG_NAMESPACE, namespace);
	}

	// get、set-------------------------------------------
	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}


	public PnoticeService getPnoticeService() {
		return pnoticeService;
	}

	public void setPnoticeService(PnoticeService pnoticeService) {
		this.pnoticeService = pnoticeService;
	}

	public List<Pnotice> getPnotices() {
		return pnotices;
	}

	public void setPnotices(List<Pnotice> pnotices) {
		this.pnotices = pnotices;
	}

	public SuccessexampleService getSuccessexampleService() {
		return successexampleService;
	}

	public void setSuccessexampleService(
			SuccessexampleService successexampleService) {
		this.successexampleService = successexampleService;
	}


	public TroubleshootingService getTroubleshootingService() {
		return troubleshootingService;
	}

	public void setTroubleshootingService(
			TroubleshootingService troubleshootingService) {
		this.troubleshootingService = troubleshootingService;
	}

	public List<Troubleshooting> getTroubleshootings() {
		return troubleshootings;
	}

	public void setTroubleshootings(List<Troubleshooting> troubleshootings) {
		this.troubleshootings = troubleshootings;
	}


	public void setCurrentUserRole(UserRole sessionCurrentUserRole) {
		this.currentUserRole = sessionCurrentUserRole;
	}

	public List<Successexample> getSuccessexamples() {
		return successexamples;
	}

	public void setSuccessexamples(List<Successexample> successexamples) {
		this.successexamples = successexamples;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}



}
