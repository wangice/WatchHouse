#!/bin/bash

host=$1
user=$2
password=$3
src_file=$4
zip_name=$5
job_name=$6
profile=$7
desc_dir=~${user}/apps
unzipPath=${desc_dir}/${job_name}


echo -e "################################################################################################################\n"
#如果遇到无法从jenkins拷贝文件到目标机上，需要将 ~root/.ssh/id_rsa.pub 的内容手动粘贴到 ~/.ssh/authorized_keys 里，并且确保目标机上~/.ssh/authorized_keys权限是600或644
echo "当前用户:"`whoami`
echo "当前目录:"`pwd`
echo -e "/sdb/jenkins/deploy.sh begin:\n"
echo "主机:"${host}
echo "用户:"${user}
#echo "密码:"*******
echo "原始文件:"${src_file}
echo "profile:"${profile}
echo "拷贝到的目标目录:"${desc_dir}
echo "解压到:"${unzipPath}
echo -e "\n"

#创建 ~/apps目录，此目录下存放发布的各文件和解压目录，只做为中转用
ssh -l ctf -t -o StrictHostKeyChecking=no ${host} "mkdir -p ${desc_dir}"

# 拷贝发布的zip文件到~/apps下
scp ${src_file} ${user}@${host}:${desc_dir}

#在~/apps下解压zip文件
ssh -l ctf -t -o StrictHostKeyChecking=no ${host} "unzip -o ${desc_dir}/${zip_name} -d ${unzipPath}"

#解压后的sh文件添加可执行权限
ssh -l ctf -t -o StrictHostKeyChecking=no ${host} "chmod -R 755 ${unzipPath}/${job_name}*.sh"

# 修改sh文件内的profile
ssh -l ctf -t -o StrictHostKeyChecking=no ${host} "sed -i "s/Profile=dev/Profile=${profile}/g" ${unzipPath}/${job_name}*.sh"

#unzip,replace prod whth ${profile},backup,uninstall,install,start
ssh -l ctf -t -o StrictHostKeyChecking=no ${host} "cd ${unzipPath}; echo '${password}'; | sudo -S ./${job_name} publish ${profile};"

#发布后删除解压的目录
ssh -l ctf -t -o StrictHostKeyChecking=no ${host} "rm -rf ${unzipPath}"

#发布后删除源zip文件
ssh -l ctf -t -o StrictHostKeyChecking=no ${host} "rm -rf ${desc_dir}/${zip_name}"

echo -e "\n/sdb/jenkins/deploy.sh end!"
echo -e "\n################################################################################################################\n"

