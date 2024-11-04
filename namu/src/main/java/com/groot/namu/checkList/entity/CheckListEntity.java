package com.groot.namu.checkList.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "check_list")
@Table(name = "check_list")
public class CheckListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long checkListId;
    private String date;
    @Email
    private String email;
    private String checkList1;
    private String checkList2;
    private String checkList3;
    private String checkList4;
    private String checkList5;
}
