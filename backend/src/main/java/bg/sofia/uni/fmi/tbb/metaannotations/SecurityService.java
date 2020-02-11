package bg.sofia.uni.fmi.tbb.metaannotations;

import bg.sofia.uni.fmi.tbb.domain.BusLinesService;
import bg.sofia.uni.fmi.tbb.model.BusLine;
import bg.sofia.uni.fmi.tbb.model.User;
import static bg.sofia.uni.fmi.tbb.model.User.BUS_COMPANY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private BusLinesService busLinesService;

    public boolean isOwner(String entityId, User principal) {
        if (principal.getRoles().contains(BUS_COMPANY)) {
            BusLine busLine = busLinesService.findById(entityId);
            return busLine.getCompanyId().equals(principal.getId());
        }
        return false;
    }
}
