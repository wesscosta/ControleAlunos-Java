/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Aluno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stepgalvao
 */
public class BD {
    private Connection con;

    public BD() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pc_2016", 
                "usuario", "senha");
    }
    
    public boolean insertAluno(Aluno a){
        
        try {
            PreparedStatement insertSTM = con.prepareStatement("INSERT INTO "
                    + "Aluno (matricula, nome, email, cpf, sexo) VALUES (?,?,?,?,?)");
            insertSTM.setInt(1,a.getMatricula());
            insertSTM.setString(2, a.getNome());
            insertSTM.setString(3, a.getEmail());
            insertSTM.setString(4, a.getCpf());
            insertSTM.setString(5, Character.toString(a.getSexo()));
            insertSTM.executeUpdate();
            insertSTM.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return true;
    }
    
    public List<Aluno> selectAll(){
        Aluno a;    
        List<Aluno> alunos = new ArrayList<>();
        try {
            
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT matricula, nome, email, cpf, sexo FROM Aluno");
            while(rs.next()){
                a = new Aluno();
                a.setMatricula(rs.getInt("matricula"));
                a.setNome(rs.getString("nome"));
                a.setEmail(rs.getString("email"));
                a.setCpf(rs.getString("cpf"));
                a.setSexo(rs.getString("sexo").charAt(0));
                alunos.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alunos;        
    }
    
    
}
