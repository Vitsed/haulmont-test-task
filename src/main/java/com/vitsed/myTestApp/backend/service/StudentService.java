package com.vitsed.myTestApp.backend.service;

import com.vitsed.myTestApp.backend.entity.Student;
import com.vitsed.myTestApp.backend.entity.StudentGroup;
import com.vitsed.myTestApp.backend.repository.GroupRepository;
import com.vitsed.myTestApp.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return studentRepository.findAll();
        } else {
            return studentRepository.search(filterText);
        }
    }

    public List<Student> findAll(Integer filterText) {
        if(filterText == null) {
            return studentRepository.findAll();
        } else {
            return studentRepository.search(filterText);
        }
    }

    public List<Student> findAll(String text, Integer number) {
        return studentRepository.search(text, number);
    }


    public long count() {
        return studentRepository.count();
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public void save(Student student) {
        if (student == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        studentRepository.save(student);
    }

    @PostConstruct
    public void populateTestData() {
        if (groupRepository.count() == 0) {
            groupRepository.saveAll(
                    Stream.of("101 ФАИТ", "203 ИТФ", "505 ФГО", "310 ИТФ")
                            .map(groupAtrb -> {
                                String[] split = groupAtrb.split(" ");
                                StudentGroup studentGroup = new StudentGroup();
                                studentGroup.setNumber(Integer.parseInt(split[0]));
                                studentGroup.setFacultyName(split[1]);
                                return studentGroup;
                            })
                            .collect(Collectors.toList()));
        }

        if (studentRepository.count() == 0) {
            Random r = new Random(0);
            List<StudentGroup> studentGroups = groupRepository.findAll();
            studentRepository.saveAll(
                    Stream.of("Иванов Иван Иванович", "Петров Петр Петрович", "Иванова Ольга Андреевна",
                            "Козлов Андрей Владимирович", "Пушкарев Дмитрий Николаевич", "Королева Наталья Николаевна",
                            "Дудь Юрий Васильевич", "Пугачева Алла Борисовна", "Никифоров Николай Денисович")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Student student = new Student();
                                student.setLastName(split[0]);
                                student.setFirstName(split[1]);
                                student.setPatronymic(split[2]);
                                student.setYearOfBirth(1985 + r.nextInt(19));
                                student.setStudentGroup(studentGroups.get(r.nextInt(studentGroups.size())));
                                return student;
                            }).collect(Collectors.toList()));
        }
    }
}
