public class MemberGroup //会员组表
{
	private Long id; //主键
	private String name; //会员组名称
	private Integer uploadAmount; //图片上传数量
	private Integer blogAmount; //单日博客量
	private Byte waterMark; //水印权限
	private Byte encrypt; //加密权限
	private Date createdAt; //创建时间
	private Date updatedAt; //更新时间
}
