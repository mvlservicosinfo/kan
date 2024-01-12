package controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.Beneficiario;
import model.Documento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.RepBeneficiario;
import repositories.RepDocumento;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value="Controller")
@RequestMapping(value="/api")
public class controller {

    @Autowired
    private RepBeneficiario repBeneficio;

    @Autowired
    private RepDocumento repDocumento;

    @ApiOperation(value="Gravar Beneficiário")
    @PostMapping(value = "/gravar")
    public void cadastrar(@RequestBody Beneficiario beneficiario, @RequestBody Documento documento){
        repBeneficio.save(beneficiario);
        repDocumento.save(documento);
    }
    @ApiOperation(value="EndpointLocalizar Todos os Beneficiários")
    @GetMapping(value="/localizartodos")
    public ResponseEntity<List<Beneficiario>> locaizarTodos(){
        List<Beneficiario> beneficiarios = repBeneficio.findAll();
        return  new ResponseEntity<List<Beneficiario>>(beneficiarios, HttpStatus.OK);
    }
    @ApiOperation(value="Endpoint Localizar or ID")
    @GetMapping(path="/localiza/{id}")
    public ResponseEntity<List<Documento>> localizaPorIDDoc(@PathVariable Long id){
        Documento documento = (Documento) repDocumento.findByIDDoc(id);
        return  ResponseEntity.ok().body(Collections.singletonList(documento));
    }

    private Map<Long, Beneficiario> beneficiarioMap = new HashMap<>();

    @ApiOperation(value="Endpoint Atualizar por ID")
    @PutMapping(path = "atualiza/{id}")
    public ResponseEntity<Beneficiario> atulizar(@PathVariable Long id, @RequestBody Beneficiario beneficiario){
        if (beneficiarioMap.containsKey(id)){
            Beneficiario existente = beneficiarioMap.get(id);
            existente.setNome(beneficiario.getNome());
            existente.setTelefone(beneficiario.getTelefone());
            existente.setDataNascimento(beneficiario.getDataNascimento());
            existente.setDataAtualizacao(beneficiario.getDataAtualizacao());
            existente.setDataInclusao(beneficiario.getDataInclusao());
            return new ResponseEntity<>(existente, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @ApiOperation(value="Endpoint Apagar por ID")
    @DeleteMapping(path="/apagar/{id}")
    public void remover(@PathVariable Long id) {
        Beneficiario beneficiario = new Beneficiario();
        Documento documento = new Documento();
        if (beneficiario.getDataInclusao().equals(documento.getDataInclusao())) {
            repBeneficio.deleteById(id);
            repDocumento.deleteById(id);
        }
    }


}
