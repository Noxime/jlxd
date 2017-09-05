package au.com.jcloud.lxd.service.impl;

import java.io.IOException;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import au.com.jcloud.lxd.model.Certificate;
import au.com.jcloud.lxd.model.Container;
import au.com.jcloud.lxd.model.Image;
import au.com.jcloud.lxd.model.ImageAlias;
import au.com.jcloud.lxd.model.Network;
import au.com.jcloud.lxd.model.Profile;
import au.com.jcloud.lxd.model.ServerInfo;
import au.com.jcloud.lxd.service.ILinuxCliService;
import au.com.jcloud.lxd.service.ILxdService;

/**
 * Created by david.vittor on 16/07/16.
 */
@Named
public class LxdServiceCliImpl extends LxdServiceImpl implements ILxdService {

	private ILinuxCliService linuxCliService;

	// ** ServerInfo **//
	@Override
	public ServerInfo getServerInfo() throws IOException, InterruptedException {
		return super.getServerInfo(); // TODO: could use "lxd info", but need to do mapping
	}

	// ** Images **//
	@Override
	public void deleteImage(String imageOrId) throws IOException, InterruptedException {
		linuxCliService.executeLinuxCmd("lxc image delete " + imageOrId);
	}

	// ** Container Operations **//
	@Override
	public void startContainer(String name) throws IOException, InterruptedException {
		linuxCliService.executeLinuxCmd("lxc start " + name);
	}

	@Override
	public void stopContainer(String name) throws IOException, InterruptedException {
		linuxCliService.executeLinuxCmd("lxc stop " + name);
	}

	@Override
	public void deleteContainer(String name) throws IOException, InterruptedException {
		linuxCliService.executeLinuxCmd("lxc delete " + name);
	}

	@Override
	public void createContainer(String newContainerName, String imageAlias) throws IOException, InterruptedException {
		linuxCliService.executeLinuxCmd("lxc launch " + imageAlias + " " + newContainerName);
	}

	@Override
	public void execOnContainer(String name, String[] commandAndArgs, String env, Boolean waitForSocket)
			throws IOException, InterruptedException {
		linuxCliService.executeLinuxCmd("lxc exec " + name + " " + StringUtils.join(commandAndArgs, " "));
	}

	// ** Operations **//

	// ** Networks **//

	// ** Certificates **//

	// ** Snapshots **//
	@Override
	public void createSnapshot(String containerName, String snapshotName) throws IOException, InterruptedException {
		super.createSnapshot(containerName, snapshotName); // TODO: Use lxc command
	}

	@Override
	public void deleteSnapshot(String containerName, String snapshotName) throws IOException, InterruptedException {
		super.deleteSnapshot(containerName, snapshotName); // TODO: Use lxc command
	}

	// ** Image Aliases **//
	@Override
	public ImageAlias getImageAlias(String name) {
		return super.getImageAlias(name); // TODO: Use lxc command
	}

	// ** File Ops **//
	@Override
	public String getFile(String containerName, String filepath) throws IOException, InterruptedException {
		linuxCliService.executeLinuxCmd("lxc file pull " + containerName + filepath + " .");
		return "";
	}

	@Override
	public Container getContainer(String name) {
		return super.getContainer(name); // TODO: Use lxc command
	}

	@Override
	public Image getImage(String nameOrId) {
		return super.getImage(nameOrId); // TODO: Use lxc command
	}

	@Override
	public Network getNetwork(String name) {
		return super.getNetwork(name); // TODO: Use lxc command
	}

	@Override
	public void deleteNetwork(String name) throws IOException, InterruptedException {
		super.deleteNetwork(name); // TODO: Use lxc command
	}

	@Override
	public Profile getProfile(String name) {
		return super.getProfile(name); // TODO: Use lxc command
	}

	@Override
	public void deleteProfile(String name) throws IOException, InterruptedException {
		super.deleteProfile(name); // TODO: Use lxc command
	}

	@Override
	public Certificate getCertificate(String name) {
		return super.getCertificate(name); // TODO: Use lxc command
	}

	public void setLinuxCliService(ILinuxCliService linuxCliService) {
		this.linuxCliService = linuxCliService;
	}
}
