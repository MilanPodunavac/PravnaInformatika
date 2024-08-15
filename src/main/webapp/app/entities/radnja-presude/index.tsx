import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import RadnjaPresude from './radnja-presude';
import RadnjaPresudeDetail from './radnja-presude-detail';
import RadnjaPresudeUpdate from './radnja-presude-update';
import RadnjaPresudeDeleteDialog from './radnja-presude-delete-dialog';

const RadnjaPresudeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<RadnjaPresude />} />
    <Route path="new" element={<RadnjaPresudeUpdate />} />
    <Route path=":id">
      <Route index element={<RadnjaPresudeDetail />} />
      <Route path="edit" element={<RadnjaPresudeUpdate />} />
      <Route path="delete" element={<RadnjaPresudeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RadnjaPresudeRoutes;
