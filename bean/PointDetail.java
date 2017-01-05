public class PointDetail //积分明细表
{
	private Long id; //主键
	private Long ownerId; //产生积分的会员ID
	private Integer amount; //产生的积分数量
	private Long pointGroupId; //所属会员组ID
	private Date createdAt; //创建时间
	private Date updatedAt; //更新时间
}
