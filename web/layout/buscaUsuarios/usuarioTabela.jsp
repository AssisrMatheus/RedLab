<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>

<div class="col s12">
    <%
        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("users");
    %>
    <table class="highlight bordered">
        <thead>
        <tr>
            <th data-field="login">Login</th>
            <th data-field="nome">Nome</th>
            <th data-field="telefone">Telefone</th>
            <th data-field="cpf">CPF</th>
            <th data-field="sexo">Sexo</th>
            <th data-field="endereco">Endere�o</th>
            <th data-field="temPlano">Tem plano</th>
        </tr>
        </thead>

        <tbody>
            <%
                if (usuarios != null) {
                    for (Usuario usu : usuarios) {
                        if (!usu.getIsAdmin()) {
                        %>
                            <tr>
                                <td><%= usu.getLogin() %></td>
                                <td><%= usu.getPessoa().getNome() %></td>
                                <td><%= usu.getPessoa().getTelefone() %></td>
                                <td><%= usu.getPessoa().getCpf() %></td>
                                <td><%= usu.getPessoa().getSexo() %></td>
                                <td><%= usu.getPessoa().getEndereco() %></td>
                                <td><%= (usu.getPessoa().getTemPlanoSaude()) ? "<i class='material-icons checkGreen'>check_circle</i>" : "<i class='material-icons cancelRed'>cancel</i>"%></td>
                            </tr>
                        <%
                        }
                    }
                }
            %>
        </tbody>
    </table>
</div>