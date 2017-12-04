package com.example.lucas.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

import bancodados.BancoDados;
import bancodados.ClienteDAO;
import model.CartaoCredito;
import model.Cliente;
import model.Endereco;

public class CadastroActivity extends AppCompatActivity
{
    ClienteDAO clienteDAO = new ClienteDAO(new BancoDados(this));
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    private void changeActivity()
    {
        if(intent != null)
        {
            finish();
            startActivity(intent);
        }
    }

    public void redirecionaLogin(String login, String senha)
    {
        intent = new Intent(this, Login2Activity.class);
        intent.putExtra(Login2Activity.intent1, login);
        intent.putExtra(Login2Activity.intent2, senha);
        changeActivity();
    }

    /**
     * Método que verifica se um dados campo EditText foi preenchido pelo usuário
     * @param text: Campo a ser verificado
     * @return false, caso o campo foi preenchido. True, caso a campo não tenha sido preenchido
     */
    private boolean isEmpty(EditText text)
    {
        if (text.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    /**
     * Método que verifica se todos os dados obrigatórios foram digitados pelo usuário
     * @return 1, caso todos os dados tenham sido digitados
     */
    private boolean verificarDados(Vector<EditText> edits)
    {
        boolean verify = true;

        for(int i = 0; verify && i < edits.size(); i++)
        {
            if(isEmpty(edits.get(i)))
            {
                verify = false;
            }
        }
        return verify;
    }

    public void criarCliente(View view)
    {
        Vector<EditText> edits = new Vector<>();

        //Dados de endereço
        EditText etEndereco = (EditText) findViewById(R.id.endereco);
        edits.add(etEndereco);
        EditText etNumero = (EditText) findViewById(R.id.num);
        edits.add(etNumero);
        EditText etCompl = (EditText) findViewById(R.id.complemento);
        edits.add(etCompl);
        //EditText etBairro = (EditText) findViewById(R.id.bairro);
        EditText etCep = (EditText) findViewById(R.id.cep);
        edits.add(etCep);
        EditText etCidade = (EditText) findViewById(R.id.cidade);
        edits.add(etCidade);
        EditText etEstado = (EditText) findViewById(R.id.estado);
        edits.add(etEstado);

        //Dados de cartão de crédito
        EditText etNomeCartao = (EditText) findViewById(R.id.nome_cartao);
        edits.add(etNomeCartao);
        EditText etNumCartao = (EditText) findViewById(R.id.num_cartao);
        edits.add(etNumCartao);
        //EditText etDatValid = (EditText) findViewById(R.id.data_validade);
        EditText etCodSegur = (EditText) findViewById(R.id.cod_seguranca);
        edits.add(etCodSegur);

        //Dados de cliente
        EditText etNomeComp = (EditText) findViewById(R.id.nomecompleto);
        edits.add(etNomeComp);
        EditText etEmail = (EditText) findViewById(R.id.email);
        edits.add(etEmail);
        EditText etCpf = (EditText) findViewById(R.id.cpf);
        edits.add(etCpf);
        EditText etDataNasc = (EditText) findViewById(R.id.dtnascimento);
        edits.add(etDataNasc);
        EditText etSexo = (EditText) findViewById(R.id.sexo);
        edits.add(etSexo);
        //EditText etTel = (EditText) findViewById(R.id.telefone);
        EditText etCel = (EditText) findViewById(R.id.celular);
        edits.add(etCel);
        EditText etLogin = (EditText) findViewById(R.id.login);
        edits.add(etLogin);
        EditText etSenha = (EditText) findViewById(R.id.senha);
        edits.add(etSenha);

        if(verificarDados(edits))
        {
            Endereco end = new Endereco(etCep.getText().toString(),
                    etEndereco.getText().toString(),
                    Integer.parseInt(etNumero.getText().toString()),
                    etCompl.getText().toString(),
                    etCidade.getText().toString(),
                    etEstado.getText().toString()
            );
            CartaoCredito cartao = new CartaoCredito(etNumCartao.getText().toString(),
                    Integer.parseInt(etCodSegur.getText().toString()),
                    1,
                    etNomeCartao.getText().toString()
            );
            Cliente cli = new Cliente(etNomeComp.getText().toString(),
                    etCpf.getText().toString(),
                    etLogin.getText().toString(),
                    etSenha.getText().toString(),
                    etEmail.getText().toString(),
                    end,
                    etDataNasc.getText().toString(),
                    etSexo.getText().toString().charAt(0),
                    etCel.getText().toString(),
                    cartao
            );
            clienteDAO.inserirCliente(cli);
            redirecionaLogin(cli.getLogin(), cli.getSenha());
        }
        else
        {
            Toast.makeText(CadastroActivity.this, "Favor digitar todos os campos!"
                    , Toast.LENGTH_LONG).show();
        }
    }
}
