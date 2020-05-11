package com.vitsed.myTestApp.backend.service;

import com.vitsed.myTestApp.backend.entity.StudentGroup;
import com.vitsed.myTestApp.backend.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GroupService {

    private static final Logger LOGGER = Logger.getLogger(GroupService.class.getName());
    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<StudentGroup> findAll() {
        return groupRepository.findAll();
    }

    public long count() {
        return groupRepository.count();
    }

    public void delete(StudentGroup studentGroup) {
        if(studentGroup.getStudents().size() == 0) {
            groupRepository.delete(studentGroup);
        } else {
            LOGGER.log(Level.SEVERE, "В группе есть студенты, удалить нельзя");
        }
    }

    public void save(StudentGroup studentGroup) {
        if (studentGroup == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        groupRepository.save(studentGroup);
    }
}
