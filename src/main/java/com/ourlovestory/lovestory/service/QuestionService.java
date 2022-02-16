package com.ourlovestory.lovestory.service;

import com.ourlovestory.lovestory.dto.PaginationDTO;
import com.ourlovestory.lovestory.dto.QuestionDTO;
import com.ourlovestory.lovestory.mapper.QuestionMapper;
import com.ourlovestory.lovestory.mapper.UserMapper;
import com.ourlovestory.lovestory.model.Question;
import com.ourlovestory.lovestory.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


//当一个请求需要组装USER表和QUESTION表时，就需要一个中间层做这个事，即Service
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        Integer totalCount = questionMapper.count();
        page = page<1? 1:page;
        page = page>(totalCount-1)/size+1? (totalCount-1)/size+1:page;
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO>questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        if (questions != null) {
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount, page, size);
        return  paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {

        Integer totalCount = questionMapper.countByUserId(userId);
        page = page<1? 1:page;
        page = page>(totalCount-1)/size+1? (totalCount-1)/size+1:page;
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO>questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        if (questions != null) {
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount, page, size);
        return  paginationDTO;
    }
}
