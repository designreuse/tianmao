package tianmao.web.app.sencode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tianmao.common.HttpCode;
import tianmao.model.user.User;
import tianmao.service.app.UserService;
import tianmao.type.VerificationCodeType;
import tianmao.web.app.util.Rest;
import tianmao.web.app.util.ValidateUtil;

/**
 * 发送验证码公共类
 *
 * @author roach
 * @date 2017/12/13
 */
@Component
public class SendCodeComponent {

    @Autowired
    private UserService userService;

    @Autowired
    private SendCommon sendCommon;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 发送验证码
     *
     * @param mobile               手机号
     * @param verificationCodeType 验证码发送类型
     * @return
     */
    public Rest send(String mobile, VerificationCodeType verificationCodeType) {
        return send(mobile, null, verificationCodeType);
    }

    /**
     * 发送验证码
     *
     * @param mobile          手机号
     * @param verifyUserExist 验证用户是否存在
     * @return
     */
    public Rest send(String mobile, Boolean verifyUserExist, VerificationCodeType verificationCodeType) {
        Rest.Builder rest = Rest.newBuilder();
        if (StringUtils.isEmpty(mobile)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("手机不能为空").build();
        }
        if (!ValidateUtil.isMobile(mobile)) {
            return rest.code(HttpCode.ILLEGAL_PARAMETERS).message("手机格式有误").build();
        }
        User user = userService.getUserByUsername(mobile);
        if (verifyUserExist != null) {
            if (verifyUserExist && user == null) {
                return rest.code(HttpCode.MOBILE_NO_REGISTERED).build();
            } else if (!verifyUserExist && user != null) {
                return rest.code(HttpCode.MOBILE_ALREADY_REGISTERED).build();
            }
        }

        //发送验证码
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sendCommon.sendCode(mobile, verificationCodeType);
            }
        });

        return rest.code(HttpCode.OK).build();
    }
}
