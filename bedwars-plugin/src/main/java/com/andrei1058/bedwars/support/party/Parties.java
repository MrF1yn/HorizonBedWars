/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2021 Andrei Dascălu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact e-mail: andrew.dascalu@gmail.com
 */

package com.andrei1058.bedwars.support.party;

import com.alessiodp.parties.api.interfaces.PartiesAPI;
import com.alessiodp.parties.api.interfaces.PartyPlayer;
import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigPath;
import com.andrei1058.bedwars.api.party.Party;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Parties implements Party {

    //Support for Parties by AlessioDP
    private final PartiesAPI api = com.alessiodp.parties.api.Parties.getApi();
    private static final int requiredRankToSelect = BedWars.config.getInt(ConfigPath.GENERAL_ALESSIODP_PARTIES_RANK);

    @Override
    public boolean hasParty(Player p) {
        PartyPlayer pp = api.getPartyPlayer(p.getUniqueId());
        return pp != null && pp.isInParty();
    }

    @Override
    public int partySize(Player p) {
        PartyPlayer pp = api.getPartyPlayer(p.getUniqueId());
        if (pp == null) return 0;
        if (pp.getPartyId() == null) return 0;
        com.alessiodp.parties.api.interfaces.Party party = api.getParty(pp.getPartyId());
        if (party == null) return 0;
        return party.getMembers().size();
    }

    @Override
    public boolean isOwner(Player p) {
        PartyPlayer pp = api.getPartyPlayer(p.getUniqueId());
        if (pp == null) return false;
        if (pp.getPartyId() == null) return false;
        return pp.getRank() >= requiredRankToSelect;
    }

    @Override
    public List<Player> getMembers(Player p) {
        ArrayList<Player> players = new ArrayList<>();

        PartyPlayer pp = api.getPartyPlayer(p.getUniqueId());
        if (pp == null) return players;
        if (pp.getPartyId() == null) return players;
        com.alessiodp.parties.api.interfaces.Party party = api.getParty(pp.getPartyId());
        if (party == null) return players;
        for (UUID pl : party.getMembers()) {
            Player on = Bukkit.getPlayer(pl);
            if (on == null) continue;
            if (!on.isOnline()) continue;
            players.add(on);
        }
        return players;
    }

    @Override
    public void createParty(Player owner, Player... members) {
    }

    @Override
    public void addMember(Player owner, Player member) {
    }

    @Override
    public void removeFromParty(Player member) {
    }

    @Override
    public void disband(Player owner) {
    }

    @Override
    public boolean isMember(Player owner, Player check) {
        PartyPlayer pp = api.getPartyPlayer(owner.getUniqueId());
        if (pp == null) return false;
        if (pp.getPartyId() == null) return false;
        com.alessiodp.parties.api.interfaces.Party party = api.getParty(pp.getPartyId());
        if (party == null) return false;
        return party.getMembers().contains(check.getUniqueId());
    }

    @Override
    public void removePlayer(Player owner, Player target) {
    }

    @Override
    public boolean isInternal() {
        return false;
    }
}
