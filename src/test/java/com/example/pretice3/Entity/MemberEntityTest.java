package com.example.pretice3.Entity;

import com.example.pretice3.dto.MemberDTO;
import com.example.pretice3.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberEntityTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void MemberInsertTest() {
        MemberEntity memberEntity = MemberEntity.builder()
                .username("홍길동")
                .build();
        System.out.println(memberRepository.save(memberEntity));
    }

    @Test
    public void MemberUpdateTest() {
        MemberEntity memberEntity = MemberEntity.builder()
                .id(1)
                .username("수진이")
                .build();
        System.out.println(memberRepository.save(memberEntity));
    }

    @Test
    public void MemberListTest() {
        List<MemberEntity> list = memberRepository.findAll();
        System.out.println(list);
    }

    @Test
    public void listOneTest() {
        Optional<MemberEntity> byId = memberRepository.findById(1);
        System.out.println(byId);
    }

    @Test
    public void testCRUDOperations() {
        //등록
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername("TestUser");
        memberEntity = memberRepository.save(memberEntity);
        System.out.println("등록 멤버 : " + memberEntity);

        //조회
        Optional<MemberEntity> savedMember = memberRepository.findById(memberEntity.getId());

        assertTrue(savedMember.isPresent());
        assertEquals("TestUser", savedMember.get().getUsername());
        System.out.println("멤버 조회 : " + savedMember.get());

        //수정
        savedMember.get().setUsername("UpdatedUser");
        memberRepository.save(savedMember.get());
        System.out.println("수정한 멤버 : " + savedMember.get());

        //수정 검증
        Optional<MemberEntity> updatedMember = memberRepository.findById(memberEntity.getId());

        assertTrue(updatedMember.isPresent());
        assertEquals("UpdatedUser", updatedMember.get().getUsername());

        System.out.println("수정검증 : " + updatedMember.get());

        //삭제
        memberRepository.deleteById(memberEntity.getId());
        System.out.println("삭제할 멤버 : " + memberEntity.getId());

        //삭제 검증
        Optional<MemberEntity> deletedMember = memberRepository.findById(memberEntity.getId());
        assertTrue(deletedMember.isEmpty());
        System.out.println("삭제검증 조회 번호 : " + memberEntity.getId() + " 없음");
    }

    @Test
    public void testDTOConversion() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUsername("DTOConversionTestUser");
        memberEntity = memberRepository.save(memberEntity);

        MemberDTO memberDTO = MemberDTO.builder()
                .id(memberEntity.getId())
                .username(memberEntity.getUsername())
                .build();

        assertEquals(memberEntity.getId(), memberDTO.getId());
        assertEquals(memberEntity.getUsername(), memberDTO.getUsername());

        System.out.println("원본 MemberEntity : " + memberEntity);
        System.out.println("변환 MemberDTO : " + memberDTO);

    }

}