export default [
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: 'login',
        path: '/user/login',
        component: './user/Login',
      },
      {
        name: 'register',
        path: '/user/register',
        component: './user/Register',
      },
      {
        component: './404',
      },
    ],
  },

  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },

  {
    path: '/admin',
    name: 'admin',
    icon: 'crown',
    access: 'canAdmin',
    component: './Admin',
    routes: [
      // {
      //   path: '/admin/sub-page',
      //   name: 'sub-page',
      //   icon: 'smile',
      //   component: './Welcome',
      // },
      {
        path: '/admin/user-manage',
        name: 'user-manage-page',
        icon: 'smile',
        component: './Admin/UserManage',
      },
      {
        component: './404',
      },
    ],
  },

  {
    name: 'list.table-list',
    icon: 'table',
    path: '/list',
    component: './TableList',
  },

  {
    path: '/',
    redirect: '/welcome',
  },


  {
    component: './404',
  },
];
