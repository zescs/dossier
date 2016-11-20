package com.zescs.dossier.config;

public final class R {
	/**
	 * 
	 * @author XIAO
	 *
	 */
	public static final class App {
		/**
		 * Security
		 */
		public static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
		/**
		 * 
		 */
		public static final String BASEPACKAGES_SCAN = "com.zescs.dossier";
		/**
		 * 
		 */
		public static final String STRATEGY = "uuid2";
		/**
		 * 路劲后缀
		 */
		public static final String MAPPING_SUFFIX = ".action";
		/**
		 * jsp视图后缀
		 */
		public static final String VIEW_SUFFIX_JSP = ".jsp";

		/**
		 * 区间后缀
		 */
		public static final String INTERVAL_SUFFIX = "_end";

		/**
		 * 区间前缀
		 */
		public static final String INTERVAL_PREFIX = "_start";
		/**
		 * 视图前缀
		 */
		public static final String VIEW_PREFIX = "/WEB-INF/views/";
		/**
		 * 
		 */
		public static final String WEBSOCKET_USER = "web_socket_user";

		public static final String FILE_PROGRESS = "fileProgress";

		/**
		 * 默认分页大小
		 */
		public static final Integer DEFAULT_PAGE_SIZE = 15;

		public static final String TYPEALIASESPACKAGE = "com.zescs.dossier.model";

		public static final String BASEPACKAGES_MAPPER = "com.zescs.dossier.repository";

		// public static final String DUBBO_REGISTRY_ADDRESS =
		// "zookeeper://192.168.1.181:2181?backup=192.168.1.182:2182,192.168.1.183:2183";
		
		public static final String DUBBO_REGISTRY_ADDRESS = "zookeeper://192.168.220.129:2181";

		public static final String DUBBO_ANNOTATION_PACKAGE = "com.zescs.dossier";

		public static final String DUBBO_SERVICE_PROTOCO_NAME = "dubbo";

		public static final String SCOPE_PROTOTYPE = "prototype";
		/**
		 * 序列化
		 */
		public static final String DUBBO_SERIALIZATION = "fst";

	}

	/**
	 * 数据相关
	 * 
	 * @author admin
	 *
	 */
	public static final class Data {
		public static final class DIC {
			/**
			 * 
			 */
			public static final String FILE_SUFFIX = "file_suffix";
			/**
			 * 
			 */
			public static final String ARTICLE_SUFFIX = "article_suffix";
			/**
			 * 档案门类代码
			 */
			public static final String CATEGORY_CODE = "category_code";

			public static final String FILE_SUFFIX_PDF = ".pdf";

			public static final String ARTICLE_SUFFIX_TXT = ".txt";
		}
	}

	/**
	 * 缓存
	 * 
	 * @author admin
	 *
	 */
	public static final class Cache {
		/**
		 * 缓存模块的配置
		 * 
		 * @author admin
		 *
		 */
		public static class Model {
			/**
			 * 菜单的缓存模块
			 */
			public static final String MENU_CACHE = "com.zescs.archives.admin.model.permissions.bean.Menu";

			public static final String ENTRY_CACHE = "com.zescs.archives.admin.model.archives.bean.Entry";
			/**
			 * 
			 */
			public static final String FUNCTION_CACHE = "com.zescs.archives.admin.model.permissions.bean.Function";
			/**
			 * 管理员的缓存模块
			 */
			public static final String ADMIN_CACHE = "com.zescs.archives.admin.model.user.bean.Admin";
		}

		/**
		 * 缓存的配置
		 * 
		 * @author admin
		 *
		 */
		public static class Config {
			/**
			 * 【配置文件的路径
			 */
			public static final String CONFIG_PATH = "/config/persistent/ehcache.xml";
		}

		public static class Key {
			/**
			 * 菜单
			 */
			public static final String DOSSIER_PERMISSIONS_MENU = "dossier_permissions_menu";
			/**
			 * 数据常量
			 */
			public static final String DOSSIER_DATA_CONSTANT = "dossier_data_constant";
			/**
			 * 用户
			 */
			public static final String DOSSIER_USER = "dossier_user";
		}
	}

	/**
	 * 
	 * @ClassName: Url
	 * @Description: TODO()
	 * @author
	 * @date 2015年6月30日 下午4:29:33
	 *
	 */
	public static final class Url {
		/**
		 * 上传文件
		 */
		public static final String FILE_SERVER_METHOD_UPLOAD = "/file/uploadFile";
		/**
		 * 
		 */
		public static final String FILE_API_SERVER_MOVE = "/file/move.action";
		/**
		 * 上传视频文件
		 */
		public static final String FILE_SERVER_VOD_UPLOAD = "/file/uploadVideoFile";
		/**
		 * 获取ip地址所在的地理位置
		 */
		public static final String IPADDRESS_API = "http://ip.taobao.com/service/getIpInfo.php";
		/**
		 * 获取ip地址所在的地理位置
		 */
		public static final String IPADDRESS_API_SINA = "http://int.dpool.sina.com.cn/iplookup/iplookup.php";
		/**
		 * 
		 */
		public static final String MAINTAIN_DATABASE_BACKUP = "maintain/database/backup.action";
	}

	/**
	 * 
	 * @ClassName: Config_Path
	 * @Description: TODO(所有的配置文件)
	 * @author Lambert
	 * @date 2015年2月6日 下午7:01:05
	 *
	 */
	public static final class Config_Path {
		/**
		 * app用户密钥文件
		 */
		public static final String RSAKEY = "rsaKey";
		/**
		 * 
		 */
		public static final String RSA_DATA_KEY = "rsaDataKey";
		/**
		 * 
		 */
		public static final String CONFIGURATION_NAME = "configuration/configuration.xml";
		/**
		 * ftp配置名称
		 */
		public static final String FTP = "ftp";
		/**
		 * 
		 */
		public static final String EXCEL_DEFAULT_TEMP_PATH = "/template/excel/defaultTemplate.xls";
		/**
		 * 
		 */
		public static final String LICENSE_VERIFY_PATH = "/configuration/key/verify/param.properties";

	}

	/**
	 * 获取系统配置
	 * 
	 * @author XIAO
	 * 
	 */
	public static final class SystemConfig {
		/**
		 * 登录用户
		 */
		public static final String LOGIN_USER = "loginUser";
		/**
		 * 系统跟路径
		 */
		public static final String CONTEXT_PTH = "context_path";
		/**
		 * 系统跟路径
		 */
		public static final String CTX = "ctx";
		/**
		 * 异常信息
		 */
		public static final String EX = "ex";
		/**
		 * 
		 */
		public static final String TEMPLATE = "/template";
		/**
		 * 登录的后台管理员用户
		 */
		public static final String LOGIN_USER_ADMIN = "loginUserAdmin";
		/**
		 * 
		 */
		public static final String SYSTEM_INIT = "system_init";
		/**
		 * 
		 */
		public static final String PUBLICK_KEY = "public_key";
		/**
		 * 
		 */
		public static final String PRIVATE_KEY = "private_key";
		/**
		 * 
		 */
		public static final String LOGIN_USER_MAP = "login_user_map";
		/**
		 * 
		 */
		public static final String LISTSKIN = "list_skin";
		/**
		 * 系统的文件路劲分隔符
		 */
		public static final String FILE_SEP = "/";
		/**
		 * 
		 */
		public static final String FILE_ROOT_PATH = FILE_SEP + "data" + FILE_SEP + "upload" + FILE_SEP;
		/**
		 * 
		 */
		public static final String PRE_AVATAR = FILE_SEP + "avatar" + FILE_SEP;
		/**
		 * 
		 */
		public static final String SERVER_HOST = "SERVER_HOST";
		/**
		 * 
		 */
		public static final String FILE_ENGARTICLE_PATH = FILE_SEP + "eng" + FILE_SEP + "Article" + FILE_SEP;
		/**
		 * 
		 */
		public static final String FILE_ARCHIVE_DOC_PATH = FILE_SEP + "archive" + FILE_SEP + "doc" + FILE_SEP;
		/**
		 * 
		 */
		public static final String FILE_ARCHIVE_VOD_PATH = FILE_SEP + "archive" + FILE_SEP + "vod" + FILE_SEP;
		/**
		 * 
		 */
		public static final String REFRESH_FLAG = "refresh_flag";
		/**
		 * 
		 */
		public static final String FILE_INDUCTION_PATH = FILE_SEP + "induction" + FILE_SEP;
	}

	/**
	 * 
	 * @ClassName: ConfigMap
	 * @Description: TODO(静态配置map)
	 * @author
	 * @date 2015年6月24日 下午10:05:39
	 *
	 */
	public static final class ConfigMap {
		public static final class Key {
			/**
			 * 
			 */
			public static final String LISTSKIN = "list_skin";
			/**
			 * 全局的页大小
			 */
			public static final String APP_PAGESIZE = "app_pageSize";
			/**
			 * 页大小
			 */
			public static final String PAGESIZE = "pageSize";
			/**
			 * 页码
			 */
			public static final String PAGEINDEX = "pageIndex";
			/**
			 * 
			 */
			public static final String SIZELIST = "sizeList";
			/**
			 * 
			 */
			public static final String FILE_UPLOAD_TOKEN = "file_upload_token";
			/**
			 * 
			 */
			public static final String SERVER_HOST = "server_host";
			/**
			 * 
			 */
			public static final String SERVER_FILE_HOST = "server_file_host";
			/**
			 * 
			 */
			public static final String RTMP_HOST = "rtmp_host";
			/**
			 * 
			 */
			public static final String RSA_KEY = "rsaKey";
			/**
			 * 
			 */
			public static final String SEARCH_KEYWORDS = "search_keywords";
			/**
			 * 
			 */
			public static final String SEARCH_DESCRIPTION = "search_description";
			/**
			 * 
			 */
			public static final String USER_SUFFIX = "user_suffix";
			/**
			 * 
			 */
			public static final String USERNAME_SUFFIX = "username_suffix";
			/**
			 * 
			 */
			public static final String FTP_FILE_ROOT = "ftp_file_root";
			/**
			 * 
			 */
			public static final String ARCHIVES_CODE = "archives_code";
			/**
			 * 
			 */
			public static final String ARCHIVES_FILE_TEMP_FOLDER = "archives_file_temp_folder";
			/**
			 * 
			 */
			public static final String NOTICE_IMAGE_FOLDER = "notice_image_folder";
			/**
			 * 
			 */
			public static final String EMAIL_IMAGE_FOLDER = "email_image_folder";
			/**
			 * 
			 */
			public static final String NOTICE_FILE_FOLDER = "notice_file_folder";
			/**
			 * 
			 */
			public static final String EMAIL_FILE_FOLDER = "email_file_folder";
			/**
			 * 
			 */
			public static final String ARCHIVES_FILE_ATTACHMENT_FOLDER = "archives_file_attachment_folder";
			/**
			 * 
			 */
			public static final String SERVER_PATH_URL = "server_path_url";
			/**
			 * 
			 */
			public static final String SERVER_API_URL = "server_api_url";
			/**
			 * 
			 */
			public static final String SERVER_SOLR_URL = "server_solr_url";
			/**
			 * 
			 */
			public static final String FTP_AVATAR_FILE_FOLDER = "ftp_avatar_file_folder";
			/**
			 * 
			 */
			public static final String FTP_ARCHIVES_FILE_FOLDER = "ftp_archives_file_folder";
			/**
			 * 
			 */
			public static final String APP_DATE_FORMAT = "app_date_format";
			/**
			 * 
			 */
			public static final String STENCIL_FOLDER = "stencil_folder";

			public static final String IDCARD_ID = "cardid";

		}

		public static final class Value {
			/**
			 * 
			 */
			public static final String DEFAULT_TEMPLATE_PATH = "/template/excel/defaultTemplate.xls";
			/**
			 * 归档文件封面
			 */
			public static final String TEMPLATE_WORD_ARCHIVES_COVER_PIGEONHOLE = "/template/word/archives/cover/pigeonhole.doc";
			/**
			 * 
			 */
			public static final String EXPORT_DEFAULT_NAME = "data.xls";
			/**
			 * 
			 */
			public static final String EXPORT_DEFAULT_WORD_NAME = "data.doc";
			/**
			 * 
			 */
			public static final String TEMP_PATH = "/resources/data/temp/";
			/**
			 * 
			 */
			public static final String ARCHIVES_ROOT_PATH = "/WEB-INF/views/archives";
			/**
			 * 权限菜单的ID
			 */
			public static final String PER_MENUID = "_perMenuId";
			/**
			 * 
			 */
			public static final Object DEFAULT_AVATAR = "default.jpg";
			/**
			 * 
			 */
			public static final String QZH = "qzh";
			/**
			 * 
			 */
			public static final String MJ = "mj";
			/**
			 * 
			 */
			public static final String BGQX = "bgqx";
			/**
			 * 
			 */
			public static final String JGWT = "jgwt";
			/**
			 * 
			 */
			public static final String AFFARIS_NOTICE_STATIC = "/WEB-INF/views/affairs/notice/";
			/**
			 * 
			 */
			public static final Integer DEFAULT_PAGESIZE = 15;
		}
	}

	/**
	 * 文件服务器的配置
	 * 
	 * @author XIAO
	 * 
	 */
	public static final class FileConfig {
		/**
		 * 文件上传进度
		 */
		public static final String FILE_PROGRESS = "fileProgress";
	}

	/**
	 * 加密
	 * 
	 * @author XIAO
	 * 
	 */
	public static final class DigestConfig {
		/**
		 * 公钥加密系数
		 */
		public static final String MODULUS = "modulus";
		/**
		 * 公钥专用指数
		 */
		public static final String EXPONENT = "exponent";
	}

	/**
	 * 项目常量的配置
	 * 
	 * @author XIAO
	 * 
	 */
	public static final class ProJectConfig {
		/**
		 * 表单验证码
		 */
		public static final String FORM_CODE = "formCode";
		/**
		 * 表单验证
		 */
		public static final String FORM_SESSION_CODE = "formSessionCode";
		/**
		 * 验证码
		 */
		public static final String SECURITY_CODE = "securityCode";
		/**
		 * 操作结果
		 */
		public static final String FLAG = "flag";
		/**
		 * 操作消息
		 */
		public static final String MESSAGE = "message";
		/**
		 * 
		 */
		public static final String FILE_PROGRESS = "";
		/**
		 * cookie写入名称
		 */
		public static final String USER_COOKIE_NAME = "userName";
		/**
		 * before
		 */
		public static final String INDEX_BEFORE = "before";
		/**
		 * after
		 */
		public static final String INDEX_AFTER = "after";
		/**
		 * after
		 */
		public static final String INDEX_ADD = "add";
	}

	/**
	 * 
	 * @ClassName: Path
	 * @Description: TODO(路径跳转)
	 * @author 郑建平
	 * @date 2014年12月18日 上午9:35:55
	 *
	 */
	public static final class Path {
		/**
		 * 
		 * @ClassName: Mpping
		 * @Description: TODO()
		 * @author 郑建平
		 * @date 2015年1月7日 下午1:20:13
		 *
		 */
		public static final class Mapping {

			/**
			 * main
			 */
			public static final String MAIN = "/main";
			/**
			 * login
			 */
			public static final String LOGIN = "/login";
			/**
			 * login
			 */
			public static final String LOGIN_ACTION = "/login" + R.App.MAPPING_SUFFIX;
			/**
			 * welcome
			 */
			public static final String WELCOME = "/welcome";
			/**
			 * 
			 */
			public static final String LOGIN_OUT = "/logout";
			// //////////////////////全局配置////////////////////////////
			/**
			 * show
			 */
			public static final String GLOBAL_SHOW = "/show";
			/**
			 * show
			 */
			public static final String GLOBAL_SHOW_ACTION = "/show" + R.App.MAPPING_SUFFIX;
			/**
			 * list
			 */
			public static final String GLOBAL_LIST = "/list";
			/**
			 * list.action
			 */
			public static final String GLOBAL_LIST_ACTION = "/list" + R.App.MAPPING_SUFFIX;
			/**
			 * count
			 */
			public static final String GLOBAL_COUNT = "/count";
			/**
			 * count.action
			 */
			public static final String GLOBAL_COUNT_ACTION = "/count" + R.App.MAPPING_SUFFIX;
			/**
			 * 
			 */
			public static final String GLOBAL_SEARCH = "/search";
			/**
			 * 
			 */
			public static final String GLOBAL_LOOK_ACTION = "/look" + R.App.MAPPING_SUFFIX;
			/**
			 * 
			 */
			public static final String GLOBAL_PREVIEW_ACTION = "/preview" + R.App.MAPPING_SUFFIX;
			/**
			 * 
			 */
			public static final String GLOBAL_SEARCH_ACTION = "/search" + R.App.MAPPING_SUFFIX;
			/**
			 * page
			 */
			public static final String GLOBAL_PAGE = "/page";
			/**
			 * 
			 */
			public static final String GLOBAL_IMPORTDATA = "/importData";
			/**
			 * 
			 */
			public static final String GLOBAL_IMPORT_EXCEL_ACTION = "/import/excel" + R.App.MAPPING_SUFFIX;
			/**
			 * add
			 */
			public static final String GLOBAL_ADD = "/add";
			/**
			 * 
			 */
			public static final String GLOBAL_ADD_ACTION = "/add" + R.App.MAPPING_SUFFIX;
			/**
			 * add
			 */
			public static final String GLOBAL_SETTING = "/setting";
			/**
			 * 
			 */
			public static final String GLOBAL_SETTING_ACTION = "/setting" + R.App.MAPPING_SUFFIX;
			/**
			 * add
			 */
			public static final String GLOBAL_DOWNLOAD = "/download";
			/**
			 * 
			 */
			public static final String GLOBAL_DOWNLOAD_ACTION = "/download" + R.App.MAPPING_SUFFIX;
			/**
			 * edit
			 */
			public static final String GLOBAL_EDIT = "/edit";
			/**
			 * edit
			 */
			public static final String GLOBAL_EDIT_ACTION = "/edit" + R.App.MAPPING_SUFFIX;
			/**
			 * 
			 */
			public static final String GLOBAL_MOVE_UP = "/move/up";
			/**
			 * 
			 */
			public static final String GLOBAL_MOVE_UP_ACTION = "/move/up" + R.App.MAPPING_SUFFIX;
			/**
			 * 
			 */
			public static final String GLOBAL_MOVE_DOWN = "/move/down";
			/**
			 * 
			 */
			public static final String GLOBAL_MOVE_DOWN_ACTION = "/move/down" + R.App.MAPPING_SUFFIX;
			/**
			 * 
			 */
			public static final String GLOBAL_GET = "/get";
			/**
			 * edit/batch
			 */
			public static final String GLOBAL_EDIT_BATCH = "/edit/batch";
			/**
			 * 
			 */
			public static final String GLOBAL_EDIT_BATCH_ACTION = "/edit/batch" + R.App.MAPPING_SUFFIX;
			/**
			 * remove
			 */
			public static final String GLOBAL_REMOVE = "/remove";
			/**
			 * 
			 */
			public static final String GLOBAL_REMOVE_ACTION = "/remove" + R.App.MAPPING_SUFFIX;
			/**
			 * delete
			 */
			public static final String GLOBAL_DELETE = "/delete";
			/**
			 * 
			 */
			public static final String GLOBAL_GETDATABYID = "/getDataById";

			/**
			 * getEditById
			 */
			public static final String GLOBAL_GETEDITBYID = "/getEditById";
			/**
			 * 
			 */
			public static final String GLOBAL_GETEDITBYID_ACTION = "/getEditById" + R.App.MAPPING_SUFFIX;
			/**
			 * fieldIsExists
			 */
			public static final String GLOBAL_FIELDISEXISTS = "/fieldIsExists";
			/**
			 * fieldIsExists
			 */
			public static final String GLOBAL_FIELDISEXISTS_ACTION = "/fieldIsExists" + R.App.MAPPING_SUFFIX;
			/**
			 * fieldIsUsed
			 */
			public static final String GLOBAL_FIELDISUSEED = "/fieldIsUsed";
			/**
			 * fieldIsUsed
			 */
			public static final String GLOBAL_FIELDISUSEED_ACTION = "/fieldIsUsed" + R.App.MAPPING_SUFFIX;
			/**
			 * detail
			 */
			public static final String GLOBAL_DETAIL = "/detail";
			/**
			 * exportExcel
			 */
			public static final String GLOBAL_EXPORT_EXCEL_ACTION = "/export/excel" + R.App.MAPPING_SUFFIX;
			/**
			 * loadMenu
			 */
			public static final String GLOBAL_LOAD_MENU = "/loadMenu";

			// ////////////////////////MODEL///////////////////////////////
			/**
			 * /permissions/menu/
			 */
			public static final String PERMISSIONS_MENU = "/permissions/menu";
			/**
			 * /system/config/
			 */
			public static final String DATA_CONSTANT = "/data/constant";
			
			/**
			 * 
			 */
			public static final String SYSTEM_CLOCK = "/system/clock";
			/**
			 * 
			 */
			public static final String SYSTEM_INDIVIDUALITY = "/system/individuality";
			/**
			 * 
			 */
			public static final String SYSTEM_PARAM = "/system/param";
			/**
			 * /user/admin/
			 */
			public static final String USER_ADMIN = "/user/admin/";
			/**
			 * /user/admin/info
			 */
			public static final String USER_ADMIN_INFO = "/user/admin/info/";
			/**
			 * /permissions/role/
			 */
			public static final String PERMISSIONS_ROLE = "/permissions/role/";
			/**
			 * /permissions/
			 */
			public static final String PERMISSIONS = "/permissions";
			/**
			 * permissions/function
			 */
			public static final String PERMISSIONS_FUNCTION = "/permissions/function/";
			/**
			 * 
			 */
			public static final String PERMISSIONS_FUNCTION_CONFIG = "/permissions/function/config/";
			// ////////////////////////菜单///////////////////////////////
			/**
			 * loadMenus
			 */
			public static final String PERMISS_MENU_LOADMENUS = "/loadMenus" + R.App.MAPPING_SUFFIX;
			/**
			 * displayNameIsExists
			 */
			public static final String PERMISS_MENU_DISPLAYNAMEISEXISTS = "/displayNameIsExists";
			// ////////////////////////角色///////////////////////////////
			/**
			 * lookMenus
			 */
			public static final String PERMISS_ROLE_LOOKMENUS = "/lookMenus";
			/**
			 * /permissions/user/role
			 */
			public static final String PERMISSIONS_USER_ROLE = "/permissions/user/role";
			// ////////////////////////user_admin///////////////////////////////
			/**
			 * freeze
			 */
			public static final String USER__ADMIN_FREEZE = "/freeze";
			/**
			 * employee
			 */
			public static final String USER_EMPLOYEE = "/user/employee/";
			/**
			 * /area/district/
			 */
			public static final String AREA_DISTRICT = "/area/district/**";
			/**
			 * /employee/bank/
			 */
			public static final String EMPLOYEE_BANK = "/employee/bank/**";
			/**
			 * 
			 */
			public static final String USER_EMPLOYEE_DEPARTURE = "/user/employee/departure/";
			/**
			 * organization/organization
			 */
			public static final String ORGANIZATION = "/organization/";
			/**
			 * 
			 */
			public static final String GLOBAL_EXPORT = "export";
			/**
			 * 
			 */
			public static final String ARTICLE_ARTICLE = "/article/article/";
			/**
			 * 
			 */
			public static final String ARTICLE_THUMBNAIL = "/article/thumbnail/";
			/**
			 * archives/docarchives
			 */
			public static final String ARCHIVES_DOCARCHIVES = "/archives/docArchives/";
			/**
			 * archives/docArchives/
			 */
			public static final String ARCHIVES_VODARCHIVES = "/archives/vodArchives/";
			/**
			 * 
			 */
			public static final String ENGINEERING_ENGVOLUMESARTICLE = "/engineering/engVolumes/engVolumesArticle";
			/**
			 * chat/user/group
			 */
			public static final String CHAT_USER_GROUP = "/chat/user/group/";
			/**
			 * chat/group/
			 */
			public static final String CHAT_GROUP = "/chat/group/";
			/**
			 * chat/friend
			 */
			public static final String CHAT_FRIEND = "/chat/friend/";
			/**
			 * 
			 */
			public static final String WAGE = "/wage/";
			/**
			 * 
			 */
			public static final String WAGE_ITEM = "/wage/item/";
			/**
			 * archives
			 */
			public static final String ARCHIVES = "/archives/";
			/**
			 * 
			 */
			public static final String ARCHIVES_FIELD = "/archives/field";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_FIELD = "/archives/config/field";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_FORM_FIELD = "/archives/config/form/field";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_FORM = "/archives/config/form";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_FORM_REPEAT = "/archives/config/form/repeat";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_QUERY_FIELD = "/archives/config/query/field";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_QUERY_CRITERIA = "/archives/config/query/criteria";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_RETRIEVE_CRITERIA = "/archives/config/retrieve/criteria";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_RETRIEVE_FIELD = "/archives/config/retrieve/field";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_QUERY_SORT = "/archives/config/query/sort";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_ARCHIVESNO = "/archives/config/archivesno";
			/**
			 * AccessInfo
			 */
			public static final String UTILIZE_ACCESSINFO = "/utilize/accessinfo";
			/**
			 * 
			 */
			public static final String BORROWI_PHYSICAL = "/borrow/physical";
			/**
			 * 
			 */
			public static final String BORROW_ELECTFILE = "/borrow/electfile";
			/**
			 * 
			 */
			public static final String BORROW_ELECTFILE_AUDITOPINION = "/borrow/electfile/auditopinion";
			/**
			 *
			 */
			public static final String AFFAIRS_BORROW_ELECTFILE_MANAGE = "/affairs/borrow/electfile/manage";
			/**
			 * AccessInfo
			 */
			public static final String ARCHIVES_ATTACHMENT = "/archives/attachment";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_ARCHIVESNO_FIELD = "/archives/config/archivesno/field";
			/**
			 * archives/entry
			 */
			public static final String ARCHIVES_ENTRY = "/archives/entry";
			/**
			 * archives/entry
			 */
			public static final String ARCHIVES_ENTRY_QUERY = "/archives/entry/query";
			/**
			 * 
			 */
			public static final String ARCHIVES_ENTRY_STATISTICAL = "/archives/entry/statistical";
			/**
			 * archives/retrieve
			 */
			public static final String ARCHIVES_ENTRY_RETRIEVE = "/archives/entry/retrieve";
			/**
			 * archives/entry
			 */
			public static final String ARCHIVES_CONFIG_QUERY = "/archives/config/query";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_ELECTFILE = "/archives/config/electfile";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_STENCIL = "/archives/config/stencil";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_STENCIL_FIELD = "/archives/config/stencil/field";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_STENCIL_FILE = "/archives/config/stencil/file";
			/**
			 * 
			 */
			public static final String ARCHIVES_CONFIG_ELECTFILE_FIELD = "/archives/config/electfile/field";
			/**
			 * archives
			 */
			public static final String ARCHIVES_ELECTFILE = "/archives/electfile";
			// //////////////////////////////////////////////////////////
			public static final String AFFAIRS_NOTICE = "/affairs/notice";
			/**
			 * 
			 */
			public static final String AFFAIRS_NOTICE_ATTACHMENT = "/affairs/notice/attachment";
			// //////////////////////////////////////////////////////////
			public static final String LOG_OPERATION = "/log/operation";
			public static final String LOG_ELECTFILE = "/log/electfile";
			// //////////////////////////////////////////////////////////
			public static final String EMAIL = "/email";
			public static final String EMAIL_ATTACH = "/email/attach";
			public static final String EMAIL_SENDBOX = "/email/sendbox";
			public static final String EMAIL_RECEIVEBOX = "/email/receivebox";
			// //////////////////////////////////////////////////////////
			public static final String MAINTAIN_DBBACK = "/maintain/dbback";
			/**
			 * 
			 */
			public static final String GLOBAL_BACKUP = "/backup";
			/**
			 * 
			 */
			public static final String GLOBAL_BACKUP_ACTION = "/backup" + R.App.MAPPING_SUFFIX;
			// //////////////////////////////////////////////////////////
			public static final String SOCKET_SYSTEM = "/websocket/system";

			public static final String SOCKET_JS_SYSTEM = "/websocket/sockjs/system";

		}

		/**
		 * 
		 * @ClassName: Views
		 * @Description: TODO(页面跳转配置)
		 * @author 郑建平
		 * @date 2015年1月7日 下午1:22:33
		 *
		 */
		public static final class Views {
			// ///////////////////////////登录//////////////////////////////
			/**
			 * 系统登录
			 */
			public static String LOGIN = "login/login";
			// ///////////////////////////main//////////////////////////////
			/**
			 * 系统主页
			 */
			public static String MAIN = "main/main";
			// ///////////////////////////welcome///////////////////////////
			/**
			 * 系统欢迎页面
			 */
			public static String WELCOME = "welcome/welcome";
			// ////////////////////////系统配置/////////////////////////////
			/**
			 * 集合
			 */
			public static String SYSTEM_CONFIG_LIST = "system/config/list";
			/**
			 * 添加页面
			 */
			public static String SYSTEM_CONFIG_ADD = "system/config/add";
			/**
			 * 修改页面
			 */
			public static String SYSTEM_CONFIG_EDIT = "system/config/edit";
			// ////////////////////////////菜单////////////////////////////////////
			/**
			 * 菜单跳转
			 */
			public static final String PERMISSIONS_MENU_LIST = "permissions/menu/list";
			/**
			 * 添加菜单
			 */
			public static final String PERMISSIONS_MENU_ADD = "permissions/menu/add";
			/**
			 * 修改菜单
			 */
			public static final String PERMISSIONS_MENU_EDIT = "permissions/menu/edit";
			// ///////////////////////////////Role/////////////////////////////////////
			/**
			 * 角色列表
			 */
			public static final String PERMISSIONS_ROLE_LIST = "permissions/role/list";
			/**
			 * 添加角色
			 */
			public static final String PERMISSIONS_ROLE_ADD = "permissions/role/add";
			/**
			 * 
			 */
			public static final String PERMISSIONS_LIST = "permissions/list";
			/**
			 * 
			 */
			public static final String PERMISSIONS_ADD = "permissions/add";
			public static final String PERMISSIONS_REMOVE = "permissions/remove";
			// ////////////////////////////user////////////////////////////////////
			/**
			 * 
			 */
			public static final String USER_ADMIN_LIST = "user/admin/list";
			/**
			 * 分配角色
			 */
			public static final String USER_ADMIN_EDITPERMISSIONS = "user/admin/editPermissions";
			/**
			 * 
			 */
			public static final String USER_ADMIN_EDITPASSWORD = "user/admin/editPwd";
			/**
			 * 
			 */
			public static final String USER_ADMIN_ADD = "user/admin/add";
			/**
			 * 
			 */
			public static final String LOGIN_OUT = "login/loginOut";
		}
	}

	/**
	 * 
	 * @ClassName: Pattern
	 * @Description: TODO(正则表达式)
	 * @author 郑建平
	 * @date 2015年1月5日 下午3:31:47
	 *
	 */
	public static final class Pattern {
		/**
		 * 手机号码正则表达式
		 */
		public static final String PHONE = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
		/**
		 * 用户名的验证
		 */
		public static final String USER_NAME = "^[a-zA-z][\\w]{2,30}";
		/**
		 * 
		 */
		public static final String DATE_FORMAT_MIN = "yyyy-MM-dd HH:mm:ss";
		/**
		 * 
		 */
		public static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
		/**
		 * 
		 */
		public static final String DATE_FORMAT_LOCAL = "yyyyMMdd";
		/**
		 * 
		 */
		public static final String DATE_FORMAT_EMAIL = "yyyy-MM-dd HH:mm";
		/**
		 * 
		 */
		public static final String DATE_FORMAT_MM = "yyyy-MM";
	}

	/**
	 * 
	 * @ClassName: Msg
	 * @Description: TODO(小溪国际化返回配置)
	 * @author 郑建平
	 * @date 2014年12月18日 上午10:37:30
	 *
	 */
	public static final class Msg {

		/**
		 * 
		 */
		public static final String ADD_FAILURE = "添加失败";
		/**
		 * 
		 */
		public static final String UPDATE_FAILURE = "修改失败";
		/**
		 * 
		 */
		public static final String REMOVE_FAILURE = "删除失败";
		/**
		 * 
		 */
		public static final String SYSTEM_ABNORMAL = "系统异常";
		/**
		 * 
		 */
		public static final String FILE_UPLOAD_MAX_FAILURE = "文件上传失败，超出指定大小";
		/**
		 * 
		 */
		public static final String UPDATE_SUCCESS = "修改成功";
		/**
		 * 
		 */
		public static final String ADD_SUCCESS = "添加成功";
		/**
		 * 
		 */
		public static final String REMOVE_SUCCESS = "删除成功";
		/**
		 * 
		 */
		public static final String VALUEUNUSED = "该值未被使用";
		/**
		 * 
		 */
		public static final String VALUEISUSED = "该值已经使用";
		/**
		 * 
		 */
		public static final String VALIDATION_FAILURE = "数据验证失败";
	}

	/**
	 * 
	 * @ClassName: Http
	 * @Description: TODO(http的先关配置类)
	 * @author 郑建平
	 * @date 2014年12月26日 下午7:39:14
	 *
	 */
	public static final class Http {
		/**
		 * 
		 * @ClassName: RequestHeader
		 * @Description: TODO(请求头信息配置类)
		 * @author 郑建平
		 * @date 2014年12月26日 下午7:39:53
		 *
		 */
		public static final class RequestHeader {
			/**
			 * 
			 */
			public static final String USER_AGENT = "User-Agent";

		}
	}

	/**
	 * 
	 * @ClassName: MailConfig
	 * @Description: TODO(邮件配置)
	 * @author 郑建平
	 * @date 2015年1月5日 下午3:31:47
	 *
	 */
	public static final class MailConfig {
		/**
		 * 服务器号
		 */
		public static final String MAIL_HOST = "smtp.163.com";
		/**
		 * 端口号
		 */
		public static final int MAIL_PORT = 25;
		/**
		 * 密码
		 */
		public static final String MAIL_PASSWORD = "zjp122991";
		/**
		 * 用户
		 */
		public static final String MAIL_USERNAME = "18190873521@163.com";
		/**
		 * 邮件发送协议
		 */
		public static final String SMTP = "smtp";
		/**
		 * 
		 */
		public static final String PASSWORD = "password";
		/**
		 * 
		 */
		public static final String USERNAME = "userName";
		/**
		 * 
		 */
		public static final String PORT = "port";
		/**
		 * 
		 */
		public static final String HOST = "host";
		/**
		 * 配置文件
		 */
		public static final String CONFIG_FILE = "mail";
		/**
		 * 
		 */
		public static final String HTML_TYPE = "text/html;charset=utf-8";
	}

	/**
	 * 
	 * @author admin
	 *
	 */
	public static final class MessageKey {
		public static final String ADMIN_LOGIN_TITLE = "admin.login.title";

		public static final String ADMIN_LOGIN_SUCCESS_MESSAGE = "user.login.success.message";

		public static final String ADMIN_LOGIN_SUCCESS_LOGINED_MESSAGE = "user.login.success.logined.message";
		// 添加
		public static final String GLOBAL_MESSAGE_OPERATION_ADD_SUCCESS = "global.message.operation.add.success";

		public static final String GLOBAL_MESSAGE_OPERATION_ADD_FAILURE = "global.message.operation.add.failure";
		// 修改
		public static final String GLOBAL_MESSAGE_OPERATION_UPDATE_SUCCESS = "global.message.operation.update.success";

		public static final String GLOBAL_MESSAGE_OPERATION_UPDATE_FAILURE = "global.message.operation.update.failure";

		// 删除
		public static final String GLOBAL_MESSAGE_OPERATION_DELEETE_SUCCESS = "global.message.operation.delete.success";

		public static final String GLOBAL_MESSAGE_OPERATION_DELETE_FAILURE = "global.message.operation.delete.failure";
		//
		public static final String GLOBAL_MESSAGE_OPERATION_ERROR = "global.message.operation.error";
		//
		public static final String GLOBAL_MESSAGE_OPERATION_FILEUPLOAD_ERROR = "global.message.operation.fileUpload.error";
		//
		public static final String GLOBAL_MESSAGE_OPERATION_VALIDATION_ERROR = "global.message.operation.validation.error";

		public static final String MESSAGE_ADMIN_LOGIN_ERROR_USER_FREEZE = "message.admin.login.error.user.freeze";

		public static final String GLOBAL_MESSAGE_ADMIN_LOGIN_ERROR_PASSWORD = "global.message.admin.login.error.password";

		public static final String GLOBAL_MESSAGE_ADMIN_LOGIN_ERROR_USER_NOTEXISTS = "global.message.admin.login.error.user.notexists";

		public static final String GLOBAL_MESSAGE_ADMIN_LOGIN_ERROR_SERURITY_CODE = "global.message.admin.login.error.securityCode";

		public static final String GLOBAL_MESSAGE_ADMIN_LOGIN_SUCCESS = "global.message.admin.login.success";

		public static final String GLOBAL_MESSAGE_ADMIN_LOGIN_ERROR = "global.message.admin.login.error";

		public static final String GLOBAL_MESSAGE_LICENSE_ERROR = "global.message.license.error";

		public static final String GLOBAL_MESSAGE_OPERATION_FORMSUBMIT_ERROR = "global.message.operation.formsubmit.error";

		public static final String GLOBAL_MESSAGE_OPERATION_IMPORT_SUCCESS = "global.message.operation.import.success";

		public static final String GLOBAL_MESSAGE_OPERATION_IMPORT_FAILURE = "global.message.operation.import.failure";

		public static final String GLOBAL_MESSAGE_OPERATION_CRETAE_SUCCESS = "global.message.operation.create.success";

		public static final String GLOBAL_MESSAGE_OPERATION_CREATE_FAILURE = "global.message.operation.create.failure";
		// generate
		public static final String GLOBAL_MESSAGE_OPERATION_GENERATE_FAILURE = "global.message.operation.generate.failure";

		public static final String GLOBAL_MESSAGE_OPERATION_GENERATE_SUCCESS = "global.message.operation.generate.success";
	}

	public static class Config {
		public static class Ftp {
			/**
			 * 超时
			 */
			public static final String DATATIMEOUT = "ftp.dataTimeOut";
			/**
			 * 编码
			 */
			public static final String ENCODING = "ftp.encoding";
			/**
			 * 端口
			 */
			public static final String PORT = "ftp.port";
			/**
			 * 隐藏文件
			 */
			public static final String ISHIDDENFILES = "ftp.isHiddenFiles";
			/**
			 * 
			 */
			public static final String IP = "ftp.ip";
			/**
			 * 
			 */
			public static final String USER = "ftp.user";
			/**
			 * 
			 */
			public static final String PASSWORD = "ftp.password";
		}

		/**
		 * 文件路径配置
		 * 
		 * @author admin
		 *
		 */
		public static class Path {
			/**
			 * 档案路径前缀
			 */
			public static final String ARCHIVES_PAGE_PREFIX = "/WEB-INF/views/archives/entry/";
			/**
			 * 
			 */
			public static final String ARCHIVES_VIEW_PREFIX = "archives/entry/";
			/**
			 * 
			 */
			public static final String ARCHIVES_ENTRY_QUERY_MAPPING = "/archives/entry/query/list.action?archivesId=";
			/**
			 * 
			 */
			public static final String ARCHIVES_ENTRY_RETRIEVE_MAPPING = "/archives/entry/retrieve/list.action?archivesId=";
			/**
			 * 
			 */
			public static final String FORM_ITEM_SPINNER = "/page/archives/field/form/spinner.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_DATEPICKER = "/page/archives/field/form/datepicker.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_COMBOBOX = "/page/archives/field/form/combobox.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_COMBOBOX_MULTI = "/page/archives/field/form/multi_combobox.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_TEXTBOX_DEFAULT = "/page/archives/field/form/textbox_default.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_TEXTBOX_AREA = "/page/archives/field/form/textbox_area.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_TEXTBOX_REMOTE_LENGTH = "/page/archives/field/form/textbox_remote_length.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_TEXTBOX_TAREEA = "/page/archives/field/form/textarea.ftl";
			/**
			 * 
			 */
			public static final String FORM_ITEM_CHECKBOX = "/page/archives/field/form/checkbox.ftl";
			/**
			 * 
			 */
			public static final String FORM_COMMON_EDIT = "/page/archives/common/edit.ftl";

			public static final String FORM_COMMON_ADD = "/page/archives/common/add.ftl";

			public static final String QUERY_ITEM_PARAM = "/page/archives/query/field/item/param.ftl";

			public static final String QUERY_ITEM_CHECKBOX = "/page/archives/query/field/item/checkbox.ftl";

			public static final String QUERY_ITEM_DATE = "/page/archives/query/field/item/date.ftl";

			public static final String QUERY_ITEM_TEXT = "/page/archives/query/field/item/text.ftl";

			public static final String QUERY_LIST_RECORDS = "/page/archives/query/field/records/list.ftl";

			public static final String QUERY_LIST_VOLUMES = "/page/archives/query/field/volumes/list.ftl";

			public static final String QUERY_LIST_PIGEONHOLE = "/page/archives/query/field/pigeonhole/list.ftl";

			public static final String QUERY_LIST_STANDARD = "/page/archives/query/field/standard/list.ftl";

			public static final String ARCHIVES_STENCIL_TEMP = "/page/archives/query/stencil/chose.ftl";

			public static final String QUERY_SEARCH_ITEM_SPINNER = "/page/archives/query/criteria/item/spinner.ftl";

			public static final String QUERY_SEARCH_ITEM_DATEPICKER = "/page/archives/query/criteria/item/datepicker.ftl";

			public static final String QUERY_SEARCH_ITEM_COMBOBOX = "/page/archives/query/criteria/item/combobox.ftl";

			public static final String QUERY_SEARCH_ITEM_COMBOBOX_MULTI = "/page/archives/query/criteria/item/multi_combobox.ftl";

			public static final String QUERY_SEARCH_ITEM_TEXTBOX_DEFAULT = "/page/archives/query/criteria/item/textbox_default.ftl";
			public static final String QUERY_SEARCH_ITEM_TEXTBOX_AREA = "/page/archives/query/criteria/item/textbox_area.ftl";

			public static final String QUERY_SEARCH_ITEM_TEXTAREA = "/page/archives/query/criteria/item/textarea.ftl";

			public static final String QUERY_SEARCH_ITEM_CHECKBOX = "/page/archives/query/criteria/item/checkbox.ftl";

			public static final String TEMPLATE_QUERY_SEARCH = "/page/archives/query/criteria/search.ftl";

			public static final String ARCHIVES_IMPORT_EXCEL = "/page/archives/import/excel.ftl";
			///////////////////////////////////////////////////////////
			public static final String RETRIEVE_ITEM_PARAM = "/page/archives/retrieve/field/item/param.ftl";

			public static final String RETRIEVE_ITEM_CHECKBOX = "/page/archives/retrieve/field/item/checkbox.ftl";

			public static final String RETRIEVE_ITEM_DATE = "/page/archives/retrieve/field/item/date.ftl";

			public static final String RETRIEVE_ITEM_TEXT = "/page/archives/retrieve/field/item/text.ftl";

			public static final String RETRIEVE_LIST_RECORDS = "/page/archives/retrieve/field/records/list.ftl";

			public static final String RETRIEVE_LIST_VOLUMES = "/page/archives/retrieve/field/volumes/list.ftl";

			public static final String RETRIEVE_LIST_PIGEONHOLE = "/page/archives/retrieve/field/pigeonhole/list.ftl";

			public static final String RETRIEVE_LIST_STANDARD = "/page/archives/retrieve/field/standard/list.ftl";

			public static final String RETRIEVE_ADD_TEMP = "/page/archives/retrieve/common/add.ftl";
			///////////////////////////////////////////////////////
			public static final String RETRIEVE_SEARCH_ITEM_SPINNER = "/page/archives/retrieve/criteria/item/spinner.ftl";

			public static final String RETRIEVE_SEARCH_ITEM_DATEPICKER = "/page/archives/retrieve/criteria/item/datepicker.ftl";

			public static final String RETRIEVE_SEARCH_ITEM_COMBOBOX = "/page/archives/retrieve/criteria/item/combobox.ftl";

			public static final String RETRIEVE_SEARCH_ITEM_COMBOBOX_MULTI = "/page/archives/retrieve/criteria/item/multi_combobox.ftl";

			public static final String RETRIEVE_SEARCH_ITEM_TEXTBOX_DEFAULT = "/page/archives/retrieve/criteria/item/textbox_default.ftl";
			public static final String RETRIEVE_SEARCH_ITEM_TEXTBOX_AREA = "/page/archives/retrieve/criteria/item/textbox_area.ftl";

			public static final String RETRIEVE_SEARCH_ITEM_TEXTAREA = "/page/archives/retrieve/criteria/item/textarea.ftl";

			public static final String RETRIEVE_SEARCH_ITEM_CHECKBOX = "/page/archives/retrieve/criteria/item/checkbox.ftl";

			public static final String AFFARIS_NOTICE_STATIC = "/page/affairs/notice/static.ftl";
		}

		public static class Nature {
			public static final String ENTRY_TITLE = "title";

			public static final String ARCHIVES_CODE_RECORDS = "rec";

			public static final String ARCHIVES_CODE_VOLUMES = "vol";

			public static final String ARCHIVES_CODE_PIGEONHOLE = "pige";

			public static final String ARCHIVES_CODE_STANDARD = "sta";

		}
	}

	/**
	 * 系统码表配置
	 * 
	 * @author admin
	 *
	 */
	public static class Clock {

		public static final String ARCHIVES_NO = "archives_no";
	}
}
