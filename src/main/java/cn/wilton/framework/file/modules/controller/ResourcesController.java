package cn.wilton.framework.file.modules.controller;

import cc.vihackerframework.core.entity.PageInfo;
import cc.vihackerframework.core.exception.ViHackerException;
import cn.wilton.framework.file.common.constant.WiltonConstant;
import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.ShareEntity;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.modules.dto.ShareQueryInput;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.modules.service.IShareService;
import cn.wilton.framework.file.properties.WiltonProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cc.vihackerframework.core.api.ViHackerResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.File;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/17
 */
@Controller
@RequestMapping("resources")
@RequiredArgsConstructor
public class ResourcesController {

    private final IFileService fileService;
    private final IShareService shareService;
    private final WiltonProperties properties;

    @GetMapping("list")
    public String page(Model model,ShareQueryInput input){
        PageInfo<ShareEntity> info = shareService.queryPage(input);
        model.addAttribute("shareList",info.getRows());
        model.addAttribute("page",info);
        return "resources-center::resources-list";
    }

    /**
     * 文件下载页面
     * @param model
     * @return
     */
    @GetMapping("/down")
    public String pickupDownload(Model model,@NotBlank Long sid,
                                 @NotBlank String sharecode,@NotBlank String pickupcode){
        ShareEntity one = shareService.getOne(new QueryWrapper<ShareEntity>()
                .eq("id", sid)
                .eq("share_code", sharecode)
                .eq("pickup_code", pickupcode)
        );
        one.setFileSizeVal(FileUtil.getSize(one.getFileSize()));
        if(ObjectUtils.isEmpty(one)){
            return "common/error/404";
        }
        model.addAttribute("share",one);
        return "pickup-download";
    }

    /**
     * 验证取件码
     * @param shareCode
     * @param pickupCode
     * @return
     */
    @GetMapping("verification")
    public@ResponseBody
    ViHackerResult<ShareEntity> getByshareCode(@NotBlank String shareCode, @NotBlank String pickupCode){
        return ViHackerResult.data(shareService.verify(shareCode,pickupCode));
    }

    /**
     * 文件下载
     * @param fileId
     * @param shareCode
     * @param pickupCode
     * @param request
     * @param response
     * @throws ViHackerException
     */
    @GetMapping("download")
    public@ResponseBody
        void downloadFile(@NotBlank Long fileId, @NotBlank String shareCode,
                             @NotBlank String pickupCode,HttpServletRequest request,
                             HttpServletResponse response) throws ViHackerException {
        shareService.verify(shareCode,pickupCode);
        FileEntity entity = fileService.getById(fileId);
        File file = new File(properties.getUserPath() + WiltonConstant.REAL_PATH
                + File.separator + entity.getStoreName());
        FileUtil.downloadFile(request,response,file,entity.getFileName(),false);
    }

}
