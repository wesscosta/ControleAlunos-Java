/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Aluno;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author stepgalvao
 */
public class AlunoDAO {
    private String path;
    Formatter out; 
    
    

    public AlunoDAO(String path) throws FileNotFoundException {
        this.path = path;
        out = new Formatter(path);
    }
    
    
    public boolean insert(Aluno a){
        out.format("%d;%s;%s;%s;%s;%s;%s;%d;%s;%s;%s;%s;%s;%s;%n", 
                a.getMatricula(),
                a.getNome(),
                a.getEmail(),
                a.getCpf(),
                a.getSexo(),
                a.getEndereco().getTipoLogradouro(),
                a.getEndereco().getNomeLogradouro(),
                a.getEndereco().getNumero(),
                a.getEndereco().getComplemento(),
                a.getEndereco().getBairro(),
                a.getEndereco().getCep(),
                a.getEndereco().getCidade(),
                a.getEndereco().getEstado(),
                a.getEndereco().getTelefon()
        );
        out.flush();
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        out.close();
    }
    
    public static List<Aluno> selectAll(String file) throws IOException{
        List<Aluno> alunos = new ArrayList<>();
        Path meuArquivo = Paths.get(file);
        Scanner s = new Scanner(meuArquivo);
        
        while(s.hasNext()){
            Aluno a = new Aluno();
            String[] campos= s.nextLine().split(";");
            a.setMatricula(Integer.valueOf(campos[0]));
            a.setNome(campos[1]);
            a.setEmail(campos[2]);
            a.setCpf(campos[3]);
            a.setSexo(campos[4].charAt(0));
            a.getEndereco().setTipoLogradouro(campos[5]);
            a.getEndereco().setNomeLogradouro(campos[6]);
            a.getEndereco().setNumero(Integer.valueOf(campos[7]));
            a.getEndereco().setComplemento(campos[8]);
            a.getEndereco().setBairro(campos[9]);
            a.getEndereco().setCep(campos[10]);
            a.getEndereco().setCidade(campos[11]);
            a.getEndereco().setEstado(campos[12]);
            a.getEndereco().setTelefon(campos[13]);
            alunos.add(a);
        }
        
        
        return alunos;
    }
    
    
}
