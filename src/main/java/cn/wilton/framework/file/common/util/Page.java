package cn.wilton.framework.file.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 手动分页
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1797982533734762417L;
    private Integer pageNum; //当前第几页数据
    private Integer pageSize; // 每页显示多少条记录
    private Integer pages; //总页数
    private Integer total; //一共多少条记录
    private Integer previousPage; //前一页
    private Integer nextPage;//后一页
    private List<T> list;//要分页的数组


    public Page(List<T> list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return;
        }

        pageNum = pageNum == null || pageNum == 0 ? 1 : pageNum;

        pageSize = pageSize == null || pageSize == 0 ? 10 : pageSize;

        this.total = list.size();

        this.pageSize = pageSize;

        this.pages = this.total / this.pageSize;
        if (this.total % this.pageSize != 0) {
            this.pages = pages + 1;
        }


        this.pageNum = this.pages < pageNum ? this.pages : pageNum;

        this.previousPage = this.pageNum > 0 ? this.pageNum - 1 : this.pageNum;

        this.nextPage = this.pageNum < pages ? this.pageNum + 1 : this.pageNum;

        // 起始索引
        int fromIndex = this.pageSize * (this.pageNum - 1);
        // 结束索引
        int toIndex = this.pageSize * this.pageNum > this.total ? this.total : this.pageSize * this.pageNum;
        //不包含结束索引的值
        this.list = list.subList(fromIndex, toIndex);

    }

}
