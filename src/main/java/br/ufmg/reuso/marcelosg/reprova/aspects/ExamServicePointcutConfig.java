package br.ufmg.reuso.marcelosg.reprova.aspects;

import org.aspectj.lang.annotation.Pointcut;

// As a best practice, centralizes pointcuts in a single place so they can also be reusable
public class ExamServicePointcutConfig {

    @Pointcut("execution(br.ufmg.reuso.marcelosg.reprova.model.Exam br.ufmg.reuso.marcelosg.reprova.service.ExamService.*(..))")
    public void returningSingleExam(){
        /*
         * Pointcut que retorna apenas um exame
         *  */
    }

    @Pointcut("execution(java.util.Collection<br.ufmg.reuso.marcelosg.reprova.model.Exam> br.ufmg.reuso.marcelosg.reprova.service.ExamService.*(..))")
    public void returningMultipleExams(){
        /*
         * Pointcut que retorna multiplos exames
         *  */
    }
}
