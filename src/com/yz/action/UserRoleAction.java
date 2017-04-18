package com.yz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.auth.AuthObject;
import com.yz.model.*;
import com.yz.service.*;
import com.yz.util.AjaxMsgUtil;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.MD5Util;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.ClueNumberVO;
import com.yz.vo.InjurycaseNumberVO;
import com.yz.vo.PersonNumberVO;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
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
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("userRoleAction")
@Scope("prototype")
public class UserRoleAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	// 分页显示
	private String[] arg = new String[2];
	private int page;
	private final int size = 10;
	private int pageCount;
	private int totalCount;

	// 条件
	private int id;
	private int con;
	private String convalue;
	private int status;// 按状态
	private int pid;// 按用户id

	// 登陆
	private String username;
	private String password;

	// 批量删除
	private String checkedIDs;

	//环境变量
	@Resource(name = "authObject")
	private AuthObject authObject;

	// service层对象
	@Resource
	private IUnitService unitService;
	@Resource
	private IUserRoleService userRoleService;
	@Resource
	private IPnoticeService pnoticeService;
	@Resource
	private ISuccessexampleService successexampleService;
	@Resource
	private IGamblingCriminalManService gamblingCriminalManService;
	@Resource
	private IGuiltSafeguardManService guiltSafeguardManService;
	@Resource
	private IAnalyzeManService analyzeManService;
	@Resource
	private IContrastManService contrastManService;
	@Resource
	private ITroubleshootingService troubleshootingService;
	@Resource
	private IPersonService personService;
	@Resource
	private IClueService clueService;
	@Resource
	private IInjurycaseService injurycaseService;

	// 单个对象
	private UserRole userRole;

	// list对象
	private List<UserRole> userRoles;
	private List<Unit> units;
	private List<Pnotice> pnotices;
	private List<Successexample> successexamples;
	private List<Person> persons;
	private List<GamblingCriminalMan> gamblingCriminalMans;
	private List<GuiltSafeguardMan> guiltSafeguardMans;
	private List<AnalyzeMan> analyzeMans;
	private List<ContrastMan> contrastMans;

	private List<Clue> clues;
	private List<Injurycase> injurycases;

	private List<Troubleshooting> troubleshootings;

	// 处理主界面人员数量信息
	private List<PersonNumberVO> personNumberVOs;
	private List<InjurycaseNumberVO> injurycaseNumberVOs;
	private List<ClueNumberVO> clueNumberVOs;

	// 个人资料新旧密码
	private String password1;
	private String password2;

	// 新增用户名,密码
	private String uname;
	private String pword;

	// 用户身份证号码
	private String cardid;

	/** ***************************** 报文公共部分 *************************** */
	/** 报文根结点 */
	private final String MSG_ROOT = "message";

	/** 报文头结点 */
	private final String MSG_HEAD = "head";

	/** 报文体结点 */
	private final String MSG_BODY = "body";

	/** 服务版本号 */
	private final String MSG_VSERSION = "version";

	/** 服务版本值 */
	private final String MSG_VSERSION_VALUE = "1.0";

	/** 服务类型 */
	private final String MSG_SERVICE_TYPE = "serviceType";

	/** 服务类型值 */
	private final String MSG_SERVICE_TYPE_VALUE = "AuthenService";

	/** 报文体 认证方式 */
	private final String MSG_AUTH_MODE = "authMode";

	/** 报文体 证书认证方式 */
	private final String MSG_AUTH_MODE_CERT_VALUE = "cert";

	/** 报文体 口令认证方式 */
	private final String MSG_AUTH_MODE_PASSWORD_VALUE = "password";

	/** 报文体 属性集 */
	private final String MSG_ATTRIBUTES = "attributes";

	/** 报文体 属性 */
	private final String MSG_ATTRIBUTE = "attr";

	/** 报文体 属性名 */
	private final String MSG_NAME = "name";

	/** 报文父级节点 */
	// --hegd
	public static final String MSG_PARENT_NAME = "parentName";

	/** 报文体 属性空间 */
	private final String MSG_NAMESPACE = "namespace";
	/** ****************************************************************** */

	/** ***************************** 请求报文 *************************** */
	/** 报文体 应用ID */
	private final String MSG_APPID = "appId";

	/** 访问控制 */
	private final String MSG_ACCESS_CONTROL = "accessControl";

	private final String MSG_ACCESS_CONTROL_TRUE = "true";

	private final String MSG_ACCESS_CONTROL_FALSE = "false";

	/** 报文体 认证结点 */
	private final String MSG_AUTH = "authen";

	/** 报文体 认证凭据 */
	private final String MSG_AUTHCREDENTIAL = "authCredential";

	/** 报文体 客户端结点 */
	private final String MSG_CLIENT_INFO = "clientInfo";

	/** 报文体 公钥证书 */
	private final String MSG_CERT_INFO = "certInfo";

	/** 报文体 客户端结点 */
	private final String MSG_CLIENT_IP = "clientIP";

	/** 报文体 detach认证请求包 */
	private final String MSG_DETACH = "detach";

	/** 报文体 原文 */
	private final String MSG_ORIGINAL = "original";

	/** 报文体 用户名 */
	private final String MSG_USERNAME = "username";

	/** 报文体 口令 */
	private final String MSG_PASSWORD = "password";

	/** 报文体 属性类型 */
	private final String MSG_ATTRIBUTE_TYPE = "attributeType";

	/** 指定属性 portion */
	private final String MSG_ATTRIBUTE_TYPE_PORTION = "portion";

	/** 指定属性 all */
	private final String MSG_ATTRIBUTE_TYPE_ALL = "all";
	/** 指定属性列表控制项 attrType */
	private final String MSG_ATTR_TYPE = "attrType";
	/** ****************************************************************** */

	/** ***************************** 响应报文 *************************** */
	/** 报文体 认证结果集状态 */
	private final String MSG_MESSAGE_STATE = "messageState";

	/** 响应报文消息码 */
	private final String MSG_MESSAGE_CODE = "messageCode";

	/** 响应报文消息描述 */
	private final String MSG_MESSAGE_DESC = "messageDesc";

	/** 报文体 认证结果集 */
	private final String MSG_AUTH_RESULT_SET = "authResultSet";

	/** 报文体 认证结果 */
	private final String MSG_AUTH_RESULT = "authResult";

	/** 报文体 认证结果状态 */
	private final String MSG_SUCCESS = "success";

	/** 报文体 认证错误码 */
	private final String MSG_AUTH_MESSSAGE_CODE = "authMessageCode";

	/** 报文体 认证错误描述 */
	private final String MSG_AUTH_MESSSAGE_DESC = "authMessageDesc";
	/** ****************************************************************** */

	/** ************************** 业务处理常量 *************************** */
	/** 认证地址 */
	private final String KEY_AUTHURL = "authURL";

	/** 应用标识 */
	private final String KEY_APP_ID = "appId";

	/** 认证方式 */
	private final String KEY_CERT_AUTHEN = "certAuthen";

	/** session中原文 */
	private final String KEY_ORIGINAL_DATA = "original_data";

	/** 客户端返回的认证原文，request中原文 */
	private final String KEY_ORIGINAL_JSP = "original_jsp";

	/** 证书认证请求包 */
	private final String KEY_SIGNED_DATA = "signed_data";

	/** 证书 */
	private final String KEY_CERT_CONTENT = "certInfo";

	/** ****************************************************************** */

	/**
	 * 用户登陆
	 * 
	 * @throws Exception
	 */
	public String login() throws Exception {

		if (checkDatebase())// 检查数据库
		{
			UserRole userRoleTest = new UserRole();
			userRoleTest.setNumber("测试人员");
			userRoleTest.setUsername("test");

			userRoleTest.setPassword(MD5Util.convertMD5(MD5Util
					.string2MD5("test")));

			userRoleTest.setUserLimit(1);
			userRoleService.add(userRoleTest);
			session.put("userRoleo", userRoleTest);
			return "loginSucc";
		}
		if (username == null || username.equals("") || password == null
				|| password.equals("")) {
			String loginfail = "用户名或密码不能为空";
			request.put("loginFail", loginfail);
			return "adminLogin";
		}
		password = MD5Util.convertMD5(MD5Util.string2MD5(password));
		UserRole userRoleLogin = userRoleService.userRolelogin(username,
				password);
		if (userRoleLogin == null) {
			String loginfail = "用户名或密码输入有误";
			request.put("loginFail", loginfail);
			return "adminLogin";
		} else {
			// 设置登陆时间
			if (session.get("userRoleo") == null) {
				setLoginTime(userRoleLogin);
				session.put("userRoleo", userRoleLogin);
			}
			// checkIP();//检查IP地址
			return "loginSucc";
		}
	}

	public String toLogin() {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String appId = authObject.getAppId();
		String authURL = authObject.getAuthURL();
		String attrType = authObject.getAttrType();
		String attributes = authObject.getAttributes();

		boolean isCompeted = isNotNull(appId) || isNotNull(attrType)
				|| isNotNull(authURL) || isNotNull(attributes);

		if (!isCompeted) {
			System.out.println("从配置文件中获得应用标识，网关地址，属性列表控制项，认证方式发生异常");
		} else {
			session.put("appId", appId);
			session.put("authURL", authURL);
			session.put("attrType", attrType);
			session.put("attributes", attributes);
		}

		String randNum = generateRandomNum();
		if (!isNotNull(randNum)) {
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

		boolean isCompeted = isNotNull(appId) || isNotNull(attrType)
				|| isNotNull(authURL) || isNotNull(attributes);

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

			if (isNotNull((String) session.get(KEY_ORIGINAL_DATA))
					&& isNotNull((String) req.getParameter(KEY_SIGNED_DATA))
					&& isNotNull((String) req.getParameter(KEY_ORIGINAL_JSP))) {
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
			if (isNotNull(errCode)) {
				request.put("errCode", errCode);
			}
			if (isNotNull(errDesc)) {
				request.put("errDesc", errDesc);
			}
			String loginfail = errDesc;
			request.put("loginFail", loginfail);
			return "adminLogin";
		} else {
			// 登陆
			UserRole userRoleLogin = userRoleService.userRolelogin(cardid);
			if (userRoleLogin == null) {
				String loginfail = "证书已失效";
				request.put("loginFail", loginfail);
				return "adminLogin";
			} else {
				// 设置登陆时间
				if (session.get("userRoleo") == null) {
					setLoginTime(userRoleLogin);
					session.put("userRoleo", userRoleLogin);
				}
				// checkIP();//检查IP地址
				return "loginSucc";
			}
		}
	}

	/*
	 * 产生认证原文
	 */
	private String generateRandomNum() {
		/***********************************************************************
		 * 第二步 服务端产生认证原文 *
		 **********************************************************************/
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

	/*
	 * 判断字符串是否为空
	 */
	private boolean isNotNull(String str) {
		if (str == null || str.replace("", " ").equals(""))
			return false;
		else
			return true;
	}

	public String changeUserRolesPassword() {
		int backNumber = -1;
		userRoles = userRoleService.getUserRoles();
		if (userRoles != null && userRoles.size() > 0) {
			for (int i = 0; i < userRoles.size(); i++) {

				UserRole userRole = userRoles.get(i);
				String newpsw = userRole.getPassword();
				userRole.setPassword(MD5Util.convertMD5(MD5Util
						.string2MD5(newpsw)));
				userRoleService.update(userRole);
			}
			backNumber = 1;
		}
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(backNumber);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String welcome() {
		// 登陆验证
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		UserRole userRoleWelcome = userRoleService.getUserRoleById(userRoleo
				.getId());
		// 欢迎界面
		pnotices = pnoticeService.getPnotices();
		successexamples = successexampleService.getSuccessexamples();// 所有
		troubleshootings = troubleshootingService.getTroubleshootings();// 所有

		/*
		 * persons = personService.getPersons();
		 * setMainPersonJspNumber(userRoleWelcome);
		 * setMainInjurycaseJspNumber(userRoleWelcome);
		 * setMainClueJspNumber(userRoleWelcome);
		 */

		return "welcome";
	}

	private void setMainClueJspNumber(UserRole userRole) {
		// TODO Auto-generated method stub
		clueNumberVOs = new ArrayList<ClueNumberVO>();

		for (int i = 1; i <= 1; i++) {
			ClueNumberVO clueNumberVO = new ClueNumberVO();
			clueNumberVO.setCtype(i);
			int number1 = getCurrentClueVONumber(i, 1, userRole);
			int number2 = getCurrentClueVONumber(i, 2, userRole);
			int number3 = getCurrentClueVONumber(i, 3, userRole);
			int number4 = number1 + number2 + number3;
			clueNumberVO.setNumber1(number1);
			clueNumberVO.setNumber2(number2);
			clueNumberVO.setNumber3(number3);
			clueNumberVO.setTotalNumber(number4);
			clueNumberVOs.add(clueNumberVO);
		}
	}

	private void setMainInjurycaseJspNumber(UserRole userRole) {
		// TODO Auto-generated method stub
		injurycaseNumberVOs = new ArrayList<InjurycaseNumberVO>();

		for (int i = 1; i <= 3; i++) {
			InjurycaseNumberVO injurycaseNumberVO = new InjurycaseNumberVO();
			injurycaseNumberVO.setItype(i);
			int number1 = getCurrentInjurycaseVONumber(i, 1, userRole);
			int number2 = getCurrentInjurycaseVONumber(i, 2, userRole);
			int number3 = getCurrentInjurycaseVONumber(i, 3, userRole);
			int number4 = number1 + number2 + number3;
			injurycaseNumberVO.setNumber1(number1);
			injurycaseNumberVO.setNumber2(number2);
			injurycaseNumberVO.setNumber3(number3);
			injurycaseNumberVO.setTotalNumber(number4);
			injurycaseNumberVOs.add(injurycaseNumberVO);
		}
	}

	// 设置人员前端显示
	private void setMainPersonJspNumber(UserRole userRole) {
		// TODO Auto-generated method stub
		personNumberVOs = new ArrayList<PersonNumberVO>();

		for (int i = 1; i <= 14; i++) {
			PersonNumberVO personNumberVO = new PersonNumberVO();
			personNumberVO.setType(i);
			int number1 = getCurrentVONumber(i, 1, userRole);
			int number2 = getCurrentVONumber(i, 2, userRole);
			int number3 = getCurrentVONumber(i, 3, userRole);
			int number4 = number1 + number2 + number3;
			personNumberVO.setNumber1(number1);
			personNumberVO.setNumber2(number2);
			personNumberVO.setNumber3(number3);
			personNumberVO.setTotalNumber(number4);
			personNumberVOs.add(personNumberVO);
		}
	}

	private int getCurrentVONumber(int type, int handleState, UserRole userRole) {

		/*
		 * persons = personService.getPersonsByTypeAndHandleState(type,
		 * handleState, userRole);
		 */
		if (persons != null) {
			return persons.size();
		} else {
			return 0;
		}

	}

	private int getCurrentClueVONumber(int ctype, int handleState,
			UserRole userRole) {
		// TODO Auto-generated method stub
		clues = clueService.getCluesByTypeAndHandleState(ctype, handleState,
				userRole);
		if (clues != null) {
			return clues.size();
		} else {
			return 0;
		}
	}

	private int getCurrentInjurycaseVONumber(int itype, int handleState,
			UserRole userRole) {
		injurycases = injurycaseService.getInjurycaseByTypeAndHandleState(
				itype, handleState, userRole);
		if (injurycases != null) {
			return injurycases.size();
		} else {
			return 0;
		}

	}

	// 设置登陆时间
	private void setLoginTime(UserRole userRoleLogin) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		units = unitService.getUnits();
		userRoles = userRoleService.getUserRoles();
		if (units.size() == 0 && userRoles.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void checkIP() {
		// TODO Auto-generated method stub
		// String ip = getIpAddr(req);
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

	/**
	 * 用户管理
	 */
	public String list() throws Exception {
		// 判断会话是否失效
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
			convalue = convalue.replace(" ", "");
		}
		if (page < 1) {
			page = 1;
		}
		// 总记录数
		totalCount = userRoleService.getTotalCount(con, convalue, userRoleo);
		// 总页数
		pageCount = userRoleService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		userRoles = userRoleService.queryList(con, convalue, userRoleo, page,
				size);
		return "list";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {

		units = unitService.getUnits();
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		// 判断回话是否失效
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo_child";
		}
		if (picture != null && pictureFileName != null
				&& !pictureFileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole", imageName, picture);
			userRole.setPhoto("/userRole" + "/" + imageName);
		}

		userRole.setUsername(uname);
		userRole.setPassword(MD5Util.convertMD5(MD5Util.string2MD5(pword)));
		userRoleService.add(userRole);

		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return "success_child";
	}

	/*
	 * 检查用户名是否存在
	 * 
	 */
	public String checkUsername() {
		if (userRoleService.getUserRoleByUserRolename(username) != null) {
			AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("该用户名已经存在,请重新输入."));
		}
		return null;
	}

	// 上传照片
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

	// 文件上传
	public void upload(String fileName, String imageName, File picture)
			throws Exception {
		File saved = new File(authObject.getFileRoot() + fileName, imageName);
		InputStream ins = null;
		OutputStream ous = null;
		try {
			saved.getParentFile().mkdirs();
			ins = new FileInputStream(picture);
			ous = new FileOutputStream(saved);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = ins.read(b)) != -1) {
				ous.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ous != null)
				ous.close();
			if (ins != null)
				ins.close();
		}
	}

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() {
		// 判断会话是否失效
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}

		userRole = userRoleService.getUserRoleById(id);
		// 删除照片
		File photofile = new File(authObject.getFileRoot()
				+ userRole.getPhoto());
		photofile.delete();

		userRoleService.delete(userRole);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteUserRoles() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			userRole = userRoleService.getUserRoleById(ids[i]);
			// 删除照片
			File photofile = new File(authObject.getFileRoot()
					+ userRole.getPhoto());
			photofile.delete();

			userRoleService.delete(userRole);
		}
		AjaxMsgUtil.outputJSONObjectToAjax(response,new AjaxMsgVO("删除成功."));
		return null;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String load() {

		userRole = userRoleService.getUserRoleById(id);
		units = unitService.getUnits();
		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo_child";
		}
		if (picture != null && pictureFileName != null
				&& !pictureFileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole", imageName, picture);
			File photofile = new File(authObject.getFileRoot()
					+ userRole.getPhoto());
			photofile.delete();
			userRole.setPhoto("/userRole" + "/" + imageName);
		}

		userRoleService.update(userRole);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return "success_child";
	}

	/**
	 * 跳转到修改秒页面
	 * 
	 * @return
	 */
	public String loadPassword() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRole");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		password = userRoleo.getPassword();
		return "password";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updatePassword() throws Exception {
		// 判断会话是否失效
		UserRole userRoleo = (UserRole) session.get("userRole");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		userRoleo.setPassword(MD5Util.convertMD5(MD5Util.string2MD5(password)));
		userRoleService.update(userRoleo);
		arg[0] = "userRoleAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
	}

	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		userRole = userRoleService.getUserRoleById(id);
		return "view";
	}

	/**
	 * 个人资料
	 */
	public String currentUserRole() {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		userRole = userRoleService.getUserRoleById(userRoleo.getId());
		;
		units = unitService.getUnits();
		return "currentUserRole";
	}

	public String updateCurrentUserRole() throws Exception {
		UserRole userRoleo = (UserRole) session.get("userRoleo");
		if (userRoleo == null) {
			return "opsessiongo";
		}
		if (picture != null && pictureFileName != null
				&& !pictureFileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));
			this.upload("/userRole", imageName, picture);
			File photofile = new File(authObject.getFileRoot()
					+ userRole.getPhoto());
			photofile.delete();
			userRole.setPhoto("/userRole" + "/" + imageName);
		}
		if (password1 != null && !password1.replace(" ", "").equals("")
				&& password2 != null && !password2.replace(" ", "").equals("")) {
			userRole.setPassword(MD5Util.convertMD5(MD5Util
					.string2MD5(password1)));
		}
		userRoleService.update(userRole);
		arg[0] = "userRoleAction!currentUserRole";
		arg[1] = "个人资料";
		return SUCCESS;
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

	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public String getConvalue() {
		return convalue;
	}

	public void setConvalue(String convalue) {
		this.convalue = convalue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public IUserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(IUserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
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

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
	}

	public void setReq(javax.servlet.http.HttpServletRequest req) {
		this.req = req;
	}

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
	}

	public IUnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(IUnitService unitService) {
		this.unitService = unitService;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public IPnoticeService getPnoticeService() {
		return pnoticeService;
	}

	public void setPnoticeService(IPnoticeService pnoticeService) {
		this.pnoticeService = pnoticeService;
	}

	public List<Pnotice> getPnotices() {
		return pnotices;
	}

	public void setPnotices(List<Pnotice> pnotices) {
		this.pnotices = pnotices;
	}

	public ISuccessexampleService getSuccessexampleService() {
		return successexampleService;
	}

	public void setSuccessexampleService(
			ISuccessexampleService successexampleService) {
		this.successexampleService = successexampleService;
	}

	public IGamblingCriminalManService getGamblingCriminalManService() {
		return gamblingCriminalManService;
	}

	public void setGamblingCriminalManService(
			IGamblingCriminalManService gamblingCriminalManService) {
		this.gamblingCriminalManService = gamblingCriminalManService;
	}

	public IGuiltSafeguardManService getGuiltSafeguardManService() {
		return guiltSafeguardManService;
	}

	public void setGuiltSafeguardManService(
			IGuiltSafeguardManService guiltSafeguardManService) {
		this.guiltSafeguardManService = guiltSafeguardManService;
	}

	public IAnalyzeManService getAnalyzeManService() {
		return analyzeManService;
	}

	public void setAnalyzeManService(IAnalyzeManService analyzeManService) {
		this.analyzeManService = analyzeManService;
	}

	public IContrastManService getContrastManService() {
		return contrastManService;
	}

	public void setContrastManService(IContrastManService contrastManService) {
		this.contrastManService = contrastManService;
	}

	public List<Successexample> getSuccessexamples() {
		return successexamples;
	}

	public void setSuccessexamples(List<Successexample> successexamples) {
		this.successexamples = successexamples;
	}

	public List<GamblingCriminalMan> getGamblingCriminalMans() {
		return gamblingCriminalMans;
	}

	public void setGamblingCriminalMans(
			List<GamblingCriminalMan> gamblingCriminalMans) {
		this.gamblingCriminalMans = gamblingCriminalMans;
	}

	public List<GuiltSafeguardMan> getGuiltSafeguardMans() {
		return guiltSafeguardMans;
	}

	public void setGuiltSafeguardMans(List<GuiltSafeguardMan> guiltSafeguardMans) {
		this.guiltSafeguardMans = guiltSafeguardMans;
	}

	public List<AnalyzeMan> getAnalyzeMans() {
		return analyzeMans;
	}

	public void setAnalyzeMans(List<AnalyzeMan> analyzeMans) {
		this.analyzeMans = analyzeMans;
	}

	public List<ContrastMan> getContrastMans() {
		return contrastMans;
	}

	public void setContrastMans(List<ContrastMan> contrastMans) {
		this.contrastMans = contrastMans;
	}

	public ITroubleshootingService getTroubleshootingService() {
		return troubleshootingService;
	}

	public void setTroubleshootingService(
			ITroubleshootingService troubleshootingService) {
		this.troubleshootingService = troubleshootingService;
	}

	public List<Troubleshooting> getTroubleshootings() {
		return troubleshootings;
	}

	public void setTroubleshootings(List<Troubleshooting> troubleshootings) {
		this.troubleshootings = troubleshootings;
	}

	public IPersonService getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<PersonNumberVO> getPersonNumberVOs() {
		return personNumberVOs;
	}

	public void setPersonNumberVOs(List<PersonNumberVO> personNumberVOs) {
		this.personNumberVOs = personNumberVOs;
	}

	public IClueService getClueService() {
		return clueService;
	}

	public void setClueService(IClueService clueService) {
		this.clueService = clueService;
	}

	public IInjurycaseService getInjurycaseService() {
		return injurycaseService;
	}

	public void setInjurycaseService(IInjurycaseService injurycaseService) {
		this.injurycaseService = injurycaseService;
	}

	public List<Clue> getClues() {
		return clues;
	}

	public void setClues(List<Clue> clues) {
		this.clues = clues;
	}

	public List<Injurycase> getInjurycases() {
		return injurycases;
	}

	public void setInjurycases(List<Injurycase> injurycases) {
		this.injurycases = injurycases;
	}

	public List<InjurycaseNumberVO> getInjurycaseNumberVOs() {
		return injurycaseNumberVOs;
	}

	public void setInjurycaseNumberVOs(
			List<InjurycaseNumberVO> injurycaseNumberVOs) {
		this.injurycaseNumberVOs = injurycaseNumberVOs;
	}

	public List<ClueNumberVO> getClueNumberVOs() {
		return clueNumberVOs;
	}

	public void setClueNumberVOs(List<ClueNumberVO> clueNumberVOs) {
		this.clueNumberVOs = clueNumberVOs;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

}
