public class Comment //评论表
{
	private Long id; //主键
	private String content; //评论内容
	private Long blogId; //评论的博客ID
	private Long ownerId; //评论作者
	private Date createdAt; //创建时间
	private Date updatedAt; //更新时间
}
