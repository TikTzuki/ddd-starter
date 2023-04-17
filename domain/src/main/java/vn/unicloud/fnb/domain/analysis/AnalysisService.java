package vn.unicloud.fnb.domain.analysis;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import vn.unicloud.fnb.dto.RevenueQuery;

@Service
@RequiredArgsConstructor
public class AnalysisService {
    private final QueryGateway queryGateway;
    public void analysisBranch(){
        Long branchId = 1L;
    }
}
