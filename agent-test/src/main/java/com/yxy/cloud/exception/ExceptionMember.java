package com.yxy.cloud.exception;



/**
 * 异常成员
 *
 * @author 29520
 * @date 2021/04/13
 */
public enum ExceptionMember {
    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    ERROR(500, "业务异常"),
    USER_LEAVE_NO_LIMIT(10000,"用户不存在限制数据"),
    USER_LEAVE_OVERRUN(10001,"用户请假次数超限"),
    SAVE_QUOTA_LOG_ERROR(10002,"记录额度流水时失败"),
    NOT_QUOTA_ERROR(10003,"不存在指定数据的额度信息"),
//    SECOND_GET_QUOTA_ERROR(10003,"第二次从缓存中获取额度时失败"),
    REST_QUOTA_ERROR(10004,"余额信息错误"),
    REST_QUOTA_Not_ENOUGH_ERROR(10005,"额度不足以扣减"),
    REST_QUOTA_Not_RECOVER_ERROR(100056,"额度回冲失败"),
    QUOTA_ID_ISEMPTY_ERROR(100051,"额度id为空"),
    RECHARGE_QUOTA_ERROR_3(10006,"回冲锁定类额度时失败，额度已达限定值"),
    RECHARGE_QUOTA_ERROR_4(10007,"回冲扣减类额度时失败，额度已达限定值"),
    RECHARGE_QUOTA_ERROR_OTHER(10008,"未知的额度操作类型，非法操作"),

    ACTIVITY_ID_NULL_ERROR(10008,"活动id为空"),

    CUSTOMUSER_NOT_QUOTA_NULL_ERROR(10009,"当前用户没有额度信息"),
    ORGORUSER_NOT_QUOTA_NULL_ERROR(10012,"没有找到额度信息"),

    ALL_QUOTA_DEDUCTION_ERROR(10010,"所有额度均操作失败"),

    ACTIVITY_RULE_NOT_FIND_ERROR(10011,"没有找到指定活动的限制条件"),
    PARAM_NULL_ERROR(10013,"参数为空"),

    UPDATE_LOG_NOT_LOG_ERROR(10014,"要更新的日志为空"),
    UPDATE_LOG_NOT_NEW_ERROR(10015,"更新的日志已不是最新状态，无法更新"),

    SID_REPEAT_NEW_ERROR(10016,"流水号重复"),
    NOT_FOUND_REPAIR_ERROR(10017,"没有可被回冲的资源"),
    NOT_FOUND_JOURNAL_ERROR(10018,"没有找到对应的流水数据"),
    JOURNAL_PROCESSED_ERROR(10019,"当前流水已被处理"),
    NOT_FOUND_JOURNAL_QUOTA_ERROR(10020,"没有找到相关的额度数据"),
    USE_QUOTA_GT_TOTAL_QUOTA(10021,"非法操作，已使用额度不可大于总额度"),
    USE_QUOTA_LT_0_QUOTA(10022,"非法操作，额度不可小于0"),
    RECHARGE_QUOTA_ERROR_NO_LOG(10023,"没有可回滚的数据"),


    NO_EQUITY_RULE(1140, "产品无相关权益规则配置"),
	POINT_SYS_POINT_CONSUME_FAIL(10041, "客户积分消费失败"),
	POINT_SYS_REQUEST_ERROR(10042, "请求积分系统失败"),
	EQUITY_SYS_CREATE_VOU_FAIL(10043, "权益平台上券失败"),
	EQUITY_SYS_REQUEST_ERROR(10044, "请求权益平台失败"),
	NO_EQUITY_PROD(1145, "无此产品"),
	QUOTA_DEC_ERR(1146, "扣减额度失败"),

    LOCAL_HAS_NOT_SYNC_DB_DATA_ERROR(1147, "本地还有未同步到数据库中的数据"),

    BATCH_REPAIR_NONE(1148, "批量回冲的原始记录不存在 或 已被回冲"),

    MANAGER_NO_QUOTA_AND_NOT_FIND_CONDITION_ERROR(1149,"当前客户经理没有额度，也没有找到活动的周期限制信息"),
    USER_NO_QUOTA_AND_NOT_FIND_CONDITION_ERROR(1150,"当前用户没有额度，也没有找到活动的周期限制信息"),
    MERCHANT_NO_QUOTA_AND_NOT_FIND_CONDITION_ERROR(1151,"当前商户没有额度，也没有找到活动的周期限制信息"),

	MANAGER_NO_NOT_EMPTY(10048, "客户经理号不能为空"),
	ORG_NO_NOT_EMPTY(10049, "机构号不能为空"),
	QUERY_TYPE_ILLEGAL(10050, "查询类型不符合要求"),
	SUB_PRODUCT_NO_NOT_EMPTY(10051, "子产品编号不能为空"),
	PRODUCT_LEVEL_NOT_EMPTY(10052, "档次不能为空"),
	NO_PRODUCT_ERROR(10053, "产品不存在或已过期"),
	MANAGER_ORG_CAN_NOT_JOIN(10054, "该客户经理或客户经理所属机构无法参与该活动"),
	WORTH_RULE_NOT_EMPTY(10055, "价值参数不能为空"),
	CUST_LIST_NOT_EMPTY(10056, "客户列表不能为空"),
	PLAN_PRODUCT_LEVEL_NOT_EMPTY(10057, "活动待办编号不为空时,档次不能为空"),
	RECV_TIME_CTRL_NOT_SET(10058, "该产品未设置发放时间控制参数"),
	BATCH_NO_NOT_EMPTY(10059, "批次号不能为空"),

	PROD_WEIGHT_NOT_SET(10060, "该券仅限特邀客户领取"),
	GET_PROD_PARAMS_FAIL(10061, "装载权益产品参数失败"),

    USER_DELIVERY_ADDRESS_INVALID_ERROR(1150, "用户收货地址无效"),

    USED_GT_TOTAL_ERROR(1152,"已使用的额度不可大于总额度"),

    ID_NO_TOTAL_ERROR(1153,"根据id无法查询到对应的总额度信息"),
    ID_NO_USER_ERROR(1154,"根据id无法查询到对应的用户额度信息"),

    QUOTA_OVERDUE(2001,"额度不可用"),
    QUOTA_CLEAR_ZERO(2002,"因额度已被清零，因此额度不可用"),
    QUOTA_NOT_BETWEEN_TIME(2003,"未到使用额度时间段"),
    QUOTA_ERROR(2004,"额度扣减失败"),

    SYSTEM_BUSY_ERROR(500001,"系统繁忙，请稍后再试"),

    RED_ACT_NOT_FIND(30001,"活动不存在"),
    RED_ACT_NO_BEGIN(30003,"本活动尚未开始"),
    RED_NO_READY(30004,"活动未就绪"),
    RED_ACT_INVALIDATION(30002,"本活动已结束"),

    RED_ACT_BATCH_LOG(30004,"数据未准备就绪"),
    RED_ACT_CUST_NOT_JOIN(30005,"您暂无参与资格"),

    RED_ACT_CLOSED(30006,"活动未开始"),
    RED_ACT_QUOTA_ERROR(30007,"您的额度已用完"),
    RED_ACT_QUOTA_INSUFFICIENT(30008,"您的额度已用完"),
    RED_ACT_FINISH(30009,"当前无可领取红包"),

    RED_NOT_EXIST_ERROR(30013,"红包不存在"),
    RED_ACT_GET_ERROR(30010,"红包已被领完"),
    RED_USED_ERROR(30011,"红包已被使用过"),
    RED_MORE_LIMIT_ERROR(30012,"当前无可领取红包"),

    CODE_NOT_FIND(40001,"二维码不存在"),
    CODE_INVALIDATION(40002,"二维码已失效"),
    CODE_COUNT_TRANSFINITE(40003,"生成二维码的次数超限"),
    GET_RED_RULE_ERROR(40004,"获取红包规则出错"),


    SYSTEM_WX_INVALID_CODE_ERROR(10055553,"无效code"),
    SYSTEM_NOT_USER(10055551,"用户不存在"),
    SYSTEM_WX_OPENID_IS_NULL(10055552,"用户的微信openid为空"),
    SYSTEM_WX_OPENID_IS_NOT_HAVE_USER(10055554,"无法获取用户信息，请重新登录"),

    HAVE_DATA_THROW_EXCEPTION(1008888111,"数据库存在指定数据，无法新增"),

    GENERAL_EXCEPTION(11111,"发生错误了"),


    CUSTOM_EXCEPTION(1,""),
    ;
    private int code;
    private String code2;
    private String msg;

    ExceptionMember(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ExceptionMember(int code, String code2, String msg) {
        this.code = code;
        this.code2 = code2;
        this.msg = msg;
    }

    public String getCode2() {
        return code2;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }

    public static ExceptionMember getValueByCode(int code){
        ExceptionMember[] values = ExceptionMember.values();
        for(ExceptionMember exceptionMember : values){
            if(exceptionMember.getCode() == code){
                return exceptionMember;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ExceptionMember{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
