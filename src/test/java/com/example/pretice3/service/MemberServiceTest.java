package com.example.pretice3.service;

import com.example.pretice3.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void MemberInsertTest() {
        //데이터 값 준비
        MemberDTO memberDTO = MemberDTO.builder()
                .username("홍길동")
                .build();
        System.out.println(memberService.createmember(memberDTO));
    }

    @Test
    public void MemberUpdateTest() {
        MemberDTO memberDTO = MemberDTO.builder()
                .id(11)
                .username("수정이")
                .build();
        System.out.println(memberService.updateMember(memberDTO));
    }


    @Test
    public void MemberListTest() {
        List<MemberDTO> list = memberService.getAllMembers();
        System.out.println(list);
    }

    @Test
    public void listOneTest() {
        MemberDTO data = memberService.getMemberById(1);
        System.out.println(data);
    }

    @Test
    public void deleteTest() {
        memberService.deleteMember(1);
    }

}