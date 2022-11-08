package com.demo.restapi.controller;

import com.demo.restapi.service.CommentService;
import com.demo.restapi.utils.request.CommentDTO;
import com.demo.restapi.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDTO> createComment(@RequestBody CommentDTO dto){
        return new ResponseEntity<>(commentService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CommentResponseDTO>>getAll(){
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CommentResponseDTO>> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentResponseDTO> update(@RequestParam("id") Long id, @RequestBody CommentDTO dto){
        return new ResponseEntity<>(commentService.update(dto,id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("deleteId") Long id){
        return new ResponseEntity<>( commentService.delete(id), HttpStatus.OK);
    }
}
