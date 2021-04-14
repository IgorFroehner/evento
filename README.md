# Evento

Sua aplicação deverá conter:

- [X] Uma opção para inserir novas tuplas da primeira tabela

- [X] Uma opção para inserir novas tuplas da segunda tabela

  * É possível inserir tuplas em artigos, tipos, edições e autores.

- [X] Uma opção para listar todas as tuplas da primeira tabela

- [X] Uma opção para listar todas as tuplas da segunda tabela
 
  * É possível listar as tabelas de artigos, tipos, edições e autores.

- [X] Uma opção para listar o resultado de uma consulta que envolva uma junção entre as duas tabelas

  * A listagem de artigos exibe os autores do artigo, que precisam ser buscados fazendo um `join` com 
    a tabela autores artigo e autores.

- [X] Uma opção para listar o resultado de uma consulta que envolva subconsulta(s) e uma ou mais funções de agregação.

    - [X] Buscar quantos artigos foram lançados de cada tipo.
    - [X] Buscar quantos artigos cada autor lançou.

# Como usar

Para utilizar essa aplicação, você deve possuir dump o banco de dados na sua máquina local.

evento_backup: https://moodle.joinville.udesc.br/pluginfile.php/6987/mod_resource/content/1/evento_backup

Após rodar o código do link em um Software gráfico para visualização de um SGBD, como o PgAdmin4, bastará fazer pequenas configurações no seu projeto para que ele se conecte corretamente ao banco.


No arquivo config -> ConnectionSingleton, é necessário inserir os seguintes dados:
nome sugerido para o db: evento
```
String url = "jdbc:postgresql://localhost:5432/{nome_do_db}";
String senha = "sua_senha_do_db";
String user = "seu_usuario_do_db";
Class.forName("org.postgresql.Driver");
connection = DriverManager.getConnection(url, user, senha);
```
