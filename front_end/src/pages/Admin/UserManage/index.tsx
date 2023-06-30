import { PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns } from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
import { Button } from 'antd';
import { useRef } from 'react';
import {searchUsers} from "@/services/ant-design-pro/api";

export const waitTimePromise = async (time: number = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

export const waitTime = async (time: number = 100) => {
  await waitTimePromise(time);
};


const columns: ProColumns<API.CurrentUser>[] = [
  {
    dataIndex: 'id',
    valueType: 'indexBorder',
    width: 48,
  },
  {
    title: 'User Name',
    dataIndex: 'username',
    copyable: false,
  },
  {
    title: 'User Account',
    dataIndex: 'userAccount',
    copyable: false,
  },
  {
    title: 'User Avatar',
    dataIndex: 'avatarUrl',
    render: (_, record) => (
      <div>
        <img src={record.avatarUrl} width={100}/>
      </div>
    ),
    copyable: false,
  },
  {
    title: 'Gender',
    dataIndex: 'gender',
    valueType: 'select',
    valueEnum: {
      0: {
        text: 'male',
      },
      1: {
        text: 'female',
      },
    },
    copyable: false,
  },
  {
    title: 'Phone',
    dataIndex: 'phone',
    copyable: false,
  },
  {
    title: 'Email',
    dataIndex: 'email',
    copyable: false,
  },
  {
    title: 'User Status',
    dataIndex: 'userStatus',
    valueType: 'select',
    valueEnum: {
      0: {
        text: 'normal',
        status: 'Success',
      },
      1: {
        text: 'error',
        status: 'Error',
      },
    },
    copyable: false,
  },
  {
    title: 'User Role',
    dataIndex: 'userRole',
    valueType: 'select',
    valueEnum: {
      0: {
        text: 'normal user',
        status: 'Default',
      },
      1: {
        text: 'administrator',
        status: 'Success',
      },
    },
    copyable: false,
  },
  {
    title: 'Creat time',
    dataIndex: 'createTime',
    valueType: 'date',
    copyable: false,
  },

];

export default () => {
  const actionRef = useRef<ActionType>();
  return (
    <ProTable<API.CurrentUser>
      columns={columns}
      actionRef={actionRef}
      cardBordered
      request={async (params = {}, sort, filter) => {
        console.log(sort, filter);
        const userList = await searchUsers();
        return {
          data: userList
        }
      }}
      editable={{
        type: 'multiple',
      }}
      columnsState={{
        persistenceKey: 'pro-table-singe-demos',
        persistenceType: 'localStorage',
        onChange(value) {
          console.log('value: ', value);
        },
      }}
      rowKey="id"
      search={{
        labelWidth: 'auto',
      }}
      options={{
        setting: {
          listsHeight: 400,
        },
      }}
      form={{
        // 由于配置了 transform，提交的参与与定义的不同这里需要转化一下
        syncToUrl: (values, type) => {
          if (type === 'get') {
            return {
              ...values,
              created_at: [values.startTime, values.endTime],
            };
          }
          return values;
        },
      }}
      pagination={{
        pageSize: 5,
        onChange: (page) => console.log(page),
      }}
      dateFormatter="string"
      headerTitle="高级表格"
      toolBarRender={() => [
        <Button
          key="button"
          icon={<PlusOutlined />}
          onClick={() => {
            actionRef.current?.reload();
          }}
          type="primary"
        >
          新建
        </Button>,
      ]}
    />
  );
};
