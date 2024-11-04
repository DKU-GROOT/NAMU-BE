package com.groot.namu.checkList.entity;

import jakarta.persistence.Column;
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
    @Column
    private String email;
    @Column
    private String checkList1;
    @Column(name = "check_list1_complete")
    private boolean checkList1Complete;
    @Column
    private String checkList2;
    @Column(name = "check_list2_complete")
    private boolean checkList2Complete;
    @Column
    private String checkList3;
    @Column(name = "check_list3_complete")
    private boolean checkList3Complete;
    @Column
    private String checkList4;
    @Column(name = "check_list4_complete")
    private boolean checkList4Complete;
    @Column
    private String checkList5;
    @Column(name = "check_list5_complete")
    private boolean checkList5Complete;
}
