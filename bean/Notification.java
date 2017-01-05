public class Notification //通知表
{
	private Long id; //主键
	private String title; //通知标题
	private String content; //通知正文
	private Long ownerId; //发布通知的管理员ID
	private Date createdAt; //创建时间
	private Date updatedAt; //更新时间
}
