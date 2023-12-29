package trabalhopoo.controllers;

import java.util.HashMap;
import java.util.Map;

public class LoginController {

    private static User user = null;

    private static final Map<String, String> credentials = new HashMap<>();

    // criação de um Map com os usuários que são capazes de fazer login
    static {

        credentials.put("Maressa", "26011997");
        credentials.put("Rodrigo", "05062002");

    }

    // usuário se torna NULL, fazendo assim a isLogado() se tornar FALSE e permitindo outro login
    public void signOut() {

        System.out.println("Signing out...");
        user = null;

    }

    // função para o usuário fazer o Login no app, recebe dois dados (Login e Pswrd)
    public boolean signIn(String login, String pswrd) {

        signOut();

        if (credentials.containsKey(login) && credentials.get(login).equals(pswrd)) {

            user = new User("Nome do Usuário", login, pswrd);

        }

        boolean isLoggedIn = isLogado();
        System.out.println("Sign in status: " + isLoggedIn);
        return isLoggedIn;

    }

    // função criada só para retornar que o valor não é NULL, podendo assim, liberar outras funções em outras partes do app
    public boolean isLogado() {

        return user != null;

    }

    // função para que o usuário logado, consiga alterar seu Username para login
    public void changeUserName(String newLogin) {
        if (isLogado() && user != null) {
            // Verifica se o novo login não está sendo utilizado por outro usuário
            if (!credentials.containsKey(newLogin)) {
                credentials.remove(user.getLogin());  // Remove o login antigo
                credentials.put(newLogin, user.getPswrd());  // Adiciona o novo login ao Map
                user.setLogin(newLogin);  // Atualiza o login do usuário logado
                System.out.println("Login alterado com sucesso! Novo login: " + user.getLogin());
            } else {
                System.out.println("O novo login já está em uso por outro usuário.");
            }

            // Desloga o usuário após mudar o login
            signOut();
        } else {
            System.out.println("Apenas o usuário logado pode mudar o login.");
        }
    }

    public void changePassword(String newPswrd) {
        if (isLogado() && user != null) {
            if (!credentials.containsValue(newPswrd)) {
                credentials.remove(user.getPswrd());

                credentials.put(user.getLogin(), newPswrd);

                user.setPswrd(newPswrd);
                System.out.println("Senha alterada com sucesso! Nova senha: " + user.getPswrd());
            } else {
                System.out.println("A nova senha já está em uso por outro usuário.");
            }

            signOut();
        } else {
            System.out.println("Apenas o usuário logado pode mudar a senha.");
        }
    }

}
