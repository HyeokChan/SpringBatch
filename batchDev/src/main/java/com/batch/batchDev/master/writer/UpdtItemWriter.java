package com.batch.batchDev.master.writer;

import com.batch.batchDev.service.service.FileService;
import com.batch.batchDev.service.service.UpdtService;
import com.batch.batchDev.service.vo.NllpVO;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * packageName    : com.batch.batchDev.master.writer
 * fileName       : UpdtItemWriter
 * author         : hyeokchan
 * date           : 2023/02/16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/02/16        hyeokchan       최초 생성
 */
@Component
public class UpdtItemWriter implements ItemWriter<NllpVO> {
    @Autowired
    private UpdtService updtService;
    @Override
    public void write(List<? extends NllpVO> items) throws Exception {
        if(items.size() > 0){
            updtService.updtNllpList((List<NllpVO>) items);
        }
    }
}