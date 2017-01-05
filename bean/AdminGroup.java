public class AdminGroup //管理员组表
{
	private Long id; //主键
	private String name; //管理员组名称
	private Byte blogCategory; //是否有管理博客分类权限
	private Byte galleryCategory; //是否有管理画廊分类权限
	private Byte memberGroup; //是否有管理会员组权限
	private Byte modifyMember; //是否有修改用户会员权限
	private Byte enterMemberBack; //是否可以进入用户后台
	private Byte postNotification; //是否有发布通知权限
	private Byte addModel; //是否有添加用户模板权限
	private Date createdAt; //创建时间
	private Date updatedAt; //更新时间
}