package project.frmr.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import project.frmr.dto.EmotionDTO;

import java.util.List;

public interface EmotionController {


    @ApiOperation("Add new data")
    public EmotionDTO save(@RequestBody EmotionDTO user);

    @ApiOperation("login")
    public ResponseEntity<?> login(HttpSession session) ;

    @ApiOperation("Find by Id")
    public EmotionDTO findById(@PathVariable("id") String id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") String id);

    @ApiOperation("Find all data")
    public List<EmotionDTO> list();

    @ApiOperation("Pagination request")
    public Page<EmotionDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public EmotionDTO update(@RequestBody EmotionDTO dto, @PathVariable("id") String id);

}
