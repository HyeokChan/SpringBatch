package com.batch.batchDev.master.writer;

import com.batch.batchDev.service.service.FileService;
import com.batch.batchDev.service.vo.NllpVO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * packageName    : com.batch.batchDev.master.writer
 * fileName       : FileItemWriter
 * author         : hyeokchan
 * date           : 2023/02/16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/16        hyeokchan       최초 생성
 */
@StepScope
public class FileItemWriter implements ItemWriter<NllpVO> {
    @Autowired
    private FileService fileService;
    @Override
    public void write(List<? extends NllpVO> items) throws Exception {
        if(items.size() > 0){

        }
    }
}
