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

### Como configurar o banco de dados

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

### Como rodar

Foi usado meaven para o desenvolvimento, então será necessária a instalação previa, a intalação no UBUNTU pode ser feita como segue:

```bash
apt get install meaven
```

E para fazer a execução do servidor localmente é possível executar diretamente da IDE (foi usado IntelliJ), ou a partir do terminal
usando o comando:

```
mvn spring-boot:run
```

Depois de terminar essa execução, é possível o acesso em `localhost:8080/`

