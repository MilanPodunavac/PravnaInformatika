import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/presuda">
        <Translate contentKey="global.menu.entities.presuda" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/osoba">
        <Translate contentKey="global.menu.entities.osoba" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/optuzeni">
        <Translate contentKey="global.menu.entities.optuzeni" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/radnja-presude">
        <Translate contentKey="global.menu.entities.radnjaPresude" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/povreda">
        <Translate contentKey="global.menu.entities.povreda" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/kazna">
        <Translate contentKey="global.menu.entities.kazna" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/zakon">
        <Translate contentKey="global.menu.entities.zakon" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/clan-zakona">
        <Translate contentKey="global.menu.entities.clanZakona" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/sud">
        <Translate contentKey="global.menu.entities.sud" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/optuznica">
        <Translate contentKey="global.menu.entities.optuznica" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
