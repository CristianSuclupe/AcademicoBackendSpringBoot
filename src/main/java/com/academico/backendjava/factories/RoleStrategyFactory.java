package com.academico.backendjava.factories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.academico.backendjava.strategies.IRoleStrategy;
import com.academico.backendjava.strategies.SecretaryRoleStrategy;
import com.academico.backendjava.strategies.TeacherRoleStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleStrategyFactory {

    private final Map<String, IRoleStrategy> strategies = new HashMap<>();

    public RoleStrategyFactory(List<IRoleStrategy> strategyList) {
        for(IRoleStrategy strategy : strategyList) {
            if(strategy instanceof TeacherRoleStrategy) {
                strategies.put("ROLE_TEACHER", strategy);
            }
            else if(strategy instanceof SecretaryRoleStrategy) {
                strategies.put("ROLE_SECRETARY", strategy);
            }
        }
    }

    public IRoleStrategy getStrategy(String roleName) {
        return Optional.ofNullable(strategies.get(roleName))
            .orElseThrow(() -> new IllegalArgumentException("No se encontró estrategia para" + roleName));
    }
}
