package com.qclaw.securitytraining.service;
import com.qclaw.securitytraining.entity.*;
import com.qclaw.securitytraining.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamPaperRepository paperRepo;
    private final QuestionRepository questionRepo;
    private final ExamRecordRepository recordRepo;
    public List<ExamPaper> getAllExamPapers() { return paperRepo.findAll(); }
    public ExamPaper getExamPaperById(Long id) { return paperRepo.findById(id).orElse(null); }
    public List<Question> getQuestionsByExamPaperId(Long pid) {
        return questionRepo.findAll().stream().filter(q -> pid.equals(q.getExamPaperId())).toList();
    }
    public ExamRecord submitExam(Long userId, Long paperId, Map<Long, String> answers) {
        List<Question> qs = getQuestionsByExamPaperId(paperId);
        int totalScore = 0;
        for (Question q : qs) {
            String ans = answers.get(q.getId());
            if (q.getAnswer().equals(ans)) totalScore += q.getScore();
        }
        ExamRecord r = new ExamRecord();
        r.setUserId(userId); r.setExamPaperId(paperId); r.setScore(totalScore);
        r.setStatus(1); r.setAnswers(answers.toString());
        return recordRepo.save(r);
    }
    public List<ExamRecord> getExamRecordsByUserId(Long userId) {
        return recordRepo.findAll().stream().filter(r -> userId.equals(r.getUserId())).toList();
    }
}