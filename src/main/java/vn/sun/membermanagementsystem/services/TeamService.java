package vn.sun.membermanagementsystem.services;

import vn.sun.membermanagementsystem.dto.response.TeamDTO;

public interface TeamService {

    TeamDTO createTeam(TeamDTO teamDTO);

    TeamDTO updateTeam(Long id, TeamDTO teamDTO);

    boolean deleteTeam(Long id);
}
