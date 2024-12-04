package br.com.gabrielsilva.todolist.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;


@Component
public class FilterTaskAuth extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authorization = request.getHeader("Authorization");

        var authEncoded = authorization.substring("Basic".length()).trim();
        System.out.println(authEncoded);

        byte[] authDecode =  Base64.getDecoder().decode(authEncoded);
        System.out.println(authDecode);

        var authString = new String(authDecode);
        System.out.println(authString);

        String[] credentials = authString.split(":");
        System.out.println(credentials[0]+"\n"+credentials[1]);
        filterChain.doFilter(request, response);
    }
}
