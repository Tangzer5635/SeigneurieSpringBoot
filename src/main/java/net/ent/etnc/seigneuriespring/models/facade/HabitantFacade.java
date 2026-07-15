package net.ent.etnc.seigneuriespring.models.facade;

import net.ent.etnc.seigneuriespring.models.entity.Habitant;
import net.ent.etnc.seigneuriespring.models.facade.exception.FacadeMetierException;

import java.util.List;

public interface HabitantFacade {

    List<Habitant> recupererLesHabitants() throws FacadeMetierException;

}