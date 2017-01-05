public class Blog //博客表
{
	private Long id; //主键
	private String title; //标题
	private String content; //正文
	private Long ownerId; //作者ID
	private Byte recommend; //是否被推荐
	private Date createdAt; //创建时间
	private Date updatedAt; //更新时间
}
