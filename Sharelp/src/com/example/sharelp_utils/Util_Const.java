package com.example.sharelp_utils;

public class Util_Const {

	//校服务器前缀：
	public static final String PREFIX_LOC="http://202.118.89.72:8080/SharelpServlet/";
	
	//本地前缀
//	public static final String PREFIX_LOC="http://192.168.23.1:8080/SharelpServlet/";
	
	//网络前缀：
	public static final String PREFIX_NET="http://sharelp.ecs11.tomcats.pw/SharelpServlet/";

	//apk下载地址
	public static final String APK=PREFIX_LOC+"apk/Sharelp.apk";
	//版本更新json地址
	public static final String VERJSON=PREFIX_LOC+"apk/ver.json";


	//登录地址
	//public static final String LOGIN="http://sharelp.ecs11.tomcats.pw/SharelpServlet/login";
	public static final String LOGIN=PREFIX_LOC+"login";
	//查找是否已存在该用户名
	public static final String SIGN_LOGIN=PREFIX_LOC+"sign_login";
	//注册
	public static final String SIGNIN=PREFIX_LOC+"signin";


	//获取share
	public static final String GETSHARE=PREFIX_NET+"share";

	public static final String EDITSHARE=PREFIX_NET+"get";

	//存入赞
	public static final String POSTZAN=PREFIX_NET+"zan";

	//存入评论
	//public static final String POSTCONMMET="http://sharelp.ecs11.tomcats.pw/SharelpServlet/getcom";
	public static final String POSTCONMMET=PREFIX_NET+"getcom";
	//读取帖子详情
	public static final String DETAILALL=PREFIX_NET+"transcom";
	//public static final String DETAILALL="http://sharelp.ecs11.tomcats.pw/SharelpServlet/all";

	
	//包含创建队伍和加入队伍
	public static final String CONTEST=PREFIX_NET+"contest";

	//点击队伍，进入队伍详情
	public static final String TEAM=PREFIX_NET+"team";

	//匹配该比赛是否有队伍
	public static final String TEAM_EXIT=PREFIX_NET+"team2";

	//进入该队伍里
	public static final String TEAM_DETAIL=PREFIX_NET+"gtm";

	//申请加入项目成员
	public static final String JOINTEAM=PREFIX_NET+"gm";

	//图片轮播
	public static final String CONTESTLIST=PREFIX_NET+"contest";

	//创建队伍
	public static final String CREATTEAM=PREFIX_NET+"getteam";

	//获得导师
	public static final String TUTOR=PREFIX_NET+"tutor";

	//提交简历
	public static final String SUB_PERSON=PREFIX_LOC+"gpersonal";
	//创建组
	public static final String SUB_TEAM=PREFIX_LOC+"gotteam";
	//获得个人
	public static final String PERSON=PREFIX_LOC+"gp";

	//获取个人简历
	public static final String PERSONAL=PREFIX_LOC+"transpersonal";
	//获取单个人简历
		public static final String SINGELPERSONAL=PREFIX_LOC+"transoneper";
	
	//获得作品
	public  static final String GTH=PREFIX_LOC+"gth";
	
	//获得校内新闻
	public  static final String News_in=PREFIX_LOC+"newsin";
	
	//获得官方新闻
		public  static final String News_out=PREFIX_LOC+"newsout";
		
		//获得校内通知
		public  static final String Note_in=PREFIX_LOC+"notein";
		
		//获得官方通知
			public  static final String Note_out=PREFIX_LOC+"noteout";
			
	
	
	//获取团队
	public static final String TEAMLIST=PREFIX_LOC+"transteamlist";
	
	//获取团队详情
    public static final String TEAM_xiangqing=PREFIX_LOC+"transteamlistdetail";


	//申请加入
	public static final String PERSONALTEAM=PREFIX_LOC+"personalteam";

	//申请加入我的
	public static final String TOME=PREFIX_LOC+"tome";
	//同意申请
	public static final String ACCEPT=PREFIX_LOC+"accept";
	public static final String REJECT=PREFIX_LOC+"reject";
	

	//我申请加入的
	public static final String METO=PREFIX_LOC+"meto";
	//上传头像
	public static final String UPLOADPHOTO = PREFIX_NET+"UploadServlet";

	public static final String MEMBERDETAIL = PREFIX_LOC+"memdetail";


	//竞赛页面轮播
	//轮播路径

	//	private  static final String PATH1="http://sharelp.ecs11.tomcats.pw/SharelpServlet/images/contest1.png";
	//private  static final String PATH2="http://sharelp.ecs11.tomcats.pw/SharelpServlet/images/contest2.png";
	//	private  static final String PATH3="http://sharelp.ecs11.tomcats.pw/SharelpServlet/images/contest1.png";;
	//	private  static final String PATH4="http://sharelp.ecs11.tomcats.pw/SharelpServlet/images/contest2.png";;




}
