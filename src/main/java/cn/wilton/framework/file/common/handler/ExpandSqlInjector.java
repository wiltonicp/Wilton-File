package cn.wilton.framework.file.common.handler;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 拓展mybatisPlus 支持批量插入
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/14
 */
@Component
public class ExpandSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}
