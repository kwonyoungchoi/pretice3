package com.example.pretice3.service;

import com.example.pretice3.Entity.MemberEntity;
import com.example.pretice3.dto.MemberDTO;
import com.example.pretice3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public MemberDTO createmember(MemberDTO memberDTO) {
        MemberEntity memberEntity = modelMapper.map(memberDTO, MemberEntity.class);
        MemberEntity savedMember = memberRepository.save(memberEntity);

        return modelMapper.map(savedMember, MemberDTO.class);
    }

    public List<MemberDTO> getAllMembers() {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        return Arrays.asList(modelMapper.map(memberEntities, MemberDTO[].class));
    }

    public MemberDTO getMemberById(Integer memberId) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(memberId);

        return modelMapper.map(optionalMember, MemberDTO.class);
    }

    public MemberDTO updateMember(MemberDTO updatedMemberDTO) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(updatedMemberDTO.getId());

        if (optionalMember.isPresent()) {
            MemberEntity existingMember = optionalMember.get();
            existingMember.setUsername(updatedMemberDTO.getUsername());
            MemberEntity updatedMember = memberRepository.save(existingMember);

            return modelMapper.map(updatedMember, MemberDTO.class);
        }
        return null;
    }

    public void deleteMember(Integer memberId) {

        memberRepository.deleteById(memberId);
    }
    
}
