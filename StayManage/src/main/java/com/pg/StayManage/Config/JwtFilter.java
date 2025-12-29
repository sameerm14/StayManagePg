package com.pg.StayManage.Config;

import com.pg.StayManage.Model.Admin;
import com.pg.StayManage.Model.Tenant;
import com.pg.StayManage.Repository.AdminRepo;
import com.pg.StayManage.Repository.TenantRepo;
import com.pg.StayManage.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private TenantRepo tenantRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Skip JWT check for public endpoints
        if (path.startsWith("/api/admin/register") ||
                path.startsWith("/api/admin/login") ||
                path.startsWith("/api/tenants/tenantregister") ||
                path.startsWith("/api/tenants/tenantlogin")) {
            filterChain.doFilter(request, response);
            return;
        }
        String FetchTokenFromHeader = request.getHeader("Authorization");

        if (FetchTokenFromHeader != null && FetchTokenFromHeader.startsWith("Bearer ")) {
            String validToken = FetchTokenFromHeader.substring(7);
            String username = jwtService.getUserName(validToken);
            Optional<Admin> admin = adminRepo.findByUsername(username);
            Optional<Tenant> tenant = tenantRepo.findByName(username);

            if (admin.isPresent()) {
                Admin admin1 = admin.get();
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(admin1, null,
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + admin1.getRole()));
                upat.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);

            } else if (tenant.isPresent()) {
                Tenant tenant1 = tenant.get();
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(tenant1, null,
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_" + tenant1.getRole()));
                upat.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }

        }

        filterChain.doFilter(request, response);
    }
}
