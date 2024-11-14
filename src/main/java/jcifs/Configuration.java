/*
 * © 2016 AgNO3 Gmbh & Co. KG
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package jcifs;


import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.List;
import java.util.TimeZone;


/**
 * 
 * 
 * Implementors of this interface should extend {@link jcifs.config.BaseConfiguration} or
 * {@link jcifs.config.DelegatingConfiguration} to get forward compatibility.
 * 
 * @author mbechler
 *
 */
public interface Configuration {

    /**
     * 
     * @return random source to use
     */
    SecureRandom getRandom ();


    /**
     * 
     * 
     * Property <code>jcifs.smb.client.dfs.ttl</code> (int, default 300)
     * 
     * @return title to live, in seconds, for DFS cache entries
     */
    long getDfsTtl ();


    /**
     * 
     * Property <code>jcifs.smb.client.dfs.strictView</code> (boolean, default false)
     * 
     * @return whether a authentication failure during DFS resolving will throw an exception
     */
    boolean isDfsStrictView ();


    /**
     * 
     * Property <code>jcifs.smb.client.dfs.disabled</code> (boolean, default false)
     * 
     * @return whether DFS lookup is disabled
     */
    boolean isDfsDisabled ();


    /**
     * Enable hack to make kerberos auth work with DFS sending short names
     * 
     * This works by appending the domain name to the netbios short name and will fail horribly if this mapping is not
     * correct for your domain.
     * 
     * Property <code>jcifs.smb.client.dfs.convertToFQDN</code> (boolean, default false)
     * 
     * @return whether to convert NetBIOS names returned by DFS to FQDNs
     */
    boolean isDfsConvertToFQDN ();


    /**
     * Minimum protocol version
     * 
     * Property <code>jcifs.smb.client.minVersion</code> (string, default SMB1)
     * 
     * @see DialectVersion
     * @return minimum protocol version to use/allow
     * @since 2.1
     */
    DialectVersion getMinimumVersion ();


    /**
     * Maximum protocol version
     * 
     * Property <code>jcifs.smb.client.maxVersion</code> (string, default SMB210)
     * 
     * @see DialectVersion
     * @return maximum protocol version to use/allow
     * @since 2.1
     */
    DialectVersion getMaximumVersion ();


    /**
     * Use SMB2 non-backward compatible negotiation style
     * 
     * Property <code>jcifs.smb.client.useSMB2Negotiation</code> (boolean, default false)
     * 
     * @return whether to use non-backward compatible protocol negotiation
     */
    boolean isUseSMB2OnlyNegotiation ();


    /**
     * Enforce secure negotiation
     * 
     * Property <code>jcifs.smb.client.requireSecureNegotiate</code> (boolean, default true)
     * 
     * This does not provide any actual downgrade protection if SMB1 is allowed.
     * 
     * It will also break connections with SMB2 servers that do not properly sign error responses.
     * 
     * @return whether to enforce the use of secure negotiation.
     */
    boolean isRequireSecureNegotiate ();


    /**
     * Enable port 139 failover
     * 
     * Property <code>jcifs.smb.client.port139.enabled</code> (boolean, default false)
     * 
     * @return whether to failover to legacy transport on port 139
     */
    boolean isPort139FailoverEnabled ();


    /**
     * 
     * Property <code>jcifs.smb.client.useUnicode</code> (boolean, default true)
     * 
     * @return whether to announce support for unicode
     */
    boolean isUseUnicode ();


    /**
     *
     * Property <code>jcifs.smb.client.forceUnicode</code> (boolean, default false)
     * 
     * @return whether to use unicode, even if the server does not announce it
     */
    boolean isForceUnicode ();


    /**
     * 
     * Property <code>jcifs.smb.client.useBatching</code> (boolean, default false)
     * 
     * @return whether to enable support for SMB1 AndX command batching
     */
    boolean isUseBatching ();


    /**
     * 
     * Property <code>jcifs.smb.client.nativeOs</code> (string, default <code>os.name</code>)
     * 
     * @return OS string to report
     */
    String getNativeOs ();


    /**
     * 
     * Property <code>jcifs.smb.client.nativeLanMan</code> (string, default <code>jCIFS</code>)
     * 
     * @return Lanman string to report
     */
    String getNativeLanman ();


    /**
     * 
     * Property <code>jcifs.smb.client.rcv_buf_size</code> (int, default 65535)
     * 
     * @return receive buffer size, in bytes
     * @deprecated use getReceiveBufferSize instead
     */
    @Deprecated
    int getRecieveBufferSize ();


    /**
     * 
     * Property <code>jcifs.smb.client.rcv_buf_size</code> (int, default 65535)
     * 
     * @return receive buffer size, in bytes
     */
    int getReceiveBufferSize ();


    /**
     * 
     * Property <code>jcifs.smb.client.snd_buf_size</code> (int, default 65535)
     * 
     * @return send buffer size, in bytes
     */
    int getSendBufferSize ();


    /**
     * 
     * Property <code>jcifs.smb.client.soTimeout</code> (int, default 35000)
     * 
     * @return socket timeout, in milliseconds
     */
    int getSoTimeout ();


    /**
     * 
     * Property <code>jcifs.smb.client.connTimeout</code> (int, default 35000)
     * 
     * @return timeout for establishing a socket connection, in milliseconds
     */
    int getConnTimeout ();


    /**
     * Property <code>jcifs.smb.client.sessionTimeout</code> (int, default 35000)
     * 
     * 
     * @return timeout for SMB sessions, in milliseconds
     */
    int getSessionTimeout ();


    /**
     * 
     * Property <code>jcifs.smb.client.responseTimeout</code> (int, default 30000)
     * 
     * @return timeout for SMB responses, in milliseconds
     */
    int getResponseTimeout ();


    /**
     * 
     * Property <code>jcifs.smb.client.lport</code> (int)
     * 
     * @return local port to use for outgoing connections
     */
    int getLocalPort ();


    /**
     * 
     * Property <code>jcifs.smb.client.laddr</code> (string)
     * 
     * @return local address to use for outgoing connections
     */
    InetAddress getLocalAddr ();


    /**
     * 
     * Property <code>jcifs.netbios.hostname</code> (string)
     * 
     * @return local NETBIOS/short name to announce
     */
    String getNetbiosHostname ();


    /**
     * 
     * Property <code>jcifs.smb.client.logonShare</code>
     * 
     * @return share to connect to during authentication, if unset connect to IPC$
     */
    String getLogonShare ();


    /**
     * 
     * 
     * Property <code>jcifs.smb.client.domain</code>
     * 
     * @return default credentials, domain name
     */
    String getDefaultDomain ();


    /**
     * 
     * Property <code>jcifs.smb.client.username</code>
     * 
     * @return default credentials, user name
     */
    String getDefaultUsername ();


    /**
     * 
     * Property <code>jcifs.smb.client.password</code>
     * 
     * @return default credentials, password
     */
    String getDefaultPassword ();


    /**
     * Lanman compatibility level
     * 
     * {@see https://technet.microsoft.com/en-us/library/cc960646.aspx}
     * 
     * 
     * <table>
     * <caption>Lanman compatibility level</caption>
     * <tr>
     * <td>0 or 1</td>
     * <td>LM and NTLM</td>
     * </tr>
     * <tr>
     * <td>2</td>
     * <td>NTLM only</td>
     * </tr>
     * <tr>
     * <td>3-5</td>
     * <td>NTLMv2 only</td>
     * </tr>
     * </table>
     * 
     * 
     * Property <code>jcifs.smb.lmCompatibility</code> (int, default 3)
     * 
     * @return lanman compatibility level, defaults to 3 i.e. NTLMv2 only
     */
    int getLanManCompatibility ();


    /**
     * 
     * Property <code>jcifs.smb.allowNTLMFallback</code> (boolean, default true)
     * 
     * @return whether to allow fallback from kerberos to NTLM
     */
    boolean isAllowNTLMFallback ();


    /**
     * Property <code>jcifs.smb.useRawNTLM</code> (boolean, default false)
     * 
     * @return whether to use raw NTLMSSP tokens instead of SPNEGO wrapped ones
     * @since 2.1
     */
    boolean isUseRawNTLM ();


    /**
     * 
     * Property <code>jcifs.smb.client.disablePlainTextPasswords</code> (boolean, default true)
     * 
     * @return whether the usage of plaintext passwords is prohibited, defaults to false
     */
    boolean isDisablePlainTextPasswords ();


    /**
     * 
     * 
     * Property <code>jcifs.resolveOrder</code> (string, default <code>LMHOSTS,DNS,WINS,BCAST</code>)
     * 
     * @return order and selection of resolver modules, see {@link ResolverType}
     */
    List<ResolverType> getResolveOrder ();


    /**
     * 
     * Property <code>jcifs.netbios.baddr</code> (string, default <code>255.255.255.255</code>)
     * 
     * @return broadcast address to use
     */
    InetAddress getBroadcastAddress ();


    /**
     * 
     * 
     * Property <code>jcifs.netbios.wins</code> (string, comma separated)
     * 
     * @return WINS server to use
     */
    InetAddress[] getWinsServers ();


    /**
     * 
     * Property <code>jcifs.netbios.lport</code> (int)
     * 
     * @return local bind port for nebios connections
     */
    int getNetbiosLocalPort ();


    /**
     * 
     * Property <code>jcifs.netbios.laddr</code> (string)
     * 
     * @return local bind address for netbios connections
     */
    InetAddress getNetbiosLocalAddress ();


    /**
     * 
     * 
     * Property <code>jcifs.netbios.soTimeout</code> (int, default 5000)
     * 
     * @return socket timeout for netbios connections, in milliseconds
     */
    int getNetbiosSoTimeout ();


    /**
     * 
     * 
     * @return virtual circuit number to use
     */
    int getVcNumber ();


    /**
     * 
     * Property <code>jcifs.smb.client.capabilities</code> (int)
     * 
     * @return custom capabilities
     */
    int getCapabilities ();


    /**
     * 
     * 
     * Property <code>jcifs.smb.client.flags2</code> (int)
     * 
     * @return custom flags2
     */
    int getFlags2 ();


    /**
     * 
     * Property <code>jcifs.smb.client.ssnLimit</code> (int, 250)
     * 
     * @return maximum number of sessions on a single connection
     */
    int getSessionLimit ();


    /**
     * 
     * Property <code>jcifs.encoding</code> (string, default <code>Cp850</code>)
     * 
     * @return OEM encoding to use
     */
    String getOemEncoding ();


    /**
     * @return local timezone
     */
    TimeZone getLocalTimezone ();


    /**
     * @return Process id to send, randomized if unset
     */
    int getPid ();


    /**
     * 
     * Property <code>jcifs.smb.client.maxMpxCount</code> (int, default 10)
     * 
     * @return maximum count of concurrent commands to announce
     */
    int getMaxMpxCount ();


    /**
     * 
     * Property <code>jcifs.smb.client.signingPreferred</code> (boolean, default false)
     * 
     * @return whether to enable SMB signing (for everything), if available
     */
    boolean isSigningEnabled ();


    /**
     * 
     * Property <code>jcifs.smb.client.ipcSigningEnforced</code> (boolean, default true)
     * 
     * @return whether to enforce SMB signing for IPC connections
     */
    boolean isIpcSigningEnforced ();


    /**
     * 
     * Property <code>jcifs.smb.client.signingEnforced</code> (boolean, default false)
     * 
     * @return whether to enforce SMB signing (for everything)
     */
    boolean isSigningEnforced ();


    /**
     * Property <code>jcifs.smb.client.encryptionEnabled</code> (boolean, default false)
     * 
     * This is an experimental option allowing to indicate support during protocol
     * negotiation, SMB encryption is not implemented yet.
     * 
     * @return whether SMB encryption is enabled
     * @since 2.1
     */
    boolean isEncryptionEnabled ();


    /**
     * 
     * Property <code>jcifs.smb.client.forceExtendedSecurity</code> (boolean, default false)
     * 
     * @return whether to force extended security usage
     */
    boolean isForceExtendedSecurity ();


    /**
     * 
     * 
     * Property <code>jcifs.netbios.lmhosts</code> (string)
     * 
     * @return lmhosts file to use
     */
    String getLmHostsFileName ();


    /**
     * 
     * Property <code>jcifs.netbios.scope</code> (string)
     * 
     * @return default netbios scope to set in requests
     */
    String getNetbiosScope ();


    /**
     * 
     * Property <code>jcifs.netbios.snd_buf_size</code> (int, default 576)
     * 
     * @return netbios send buffer size
     */
    int getNetbiosSndBufSize ();


    /**
     * 
     * Property <code>jcifs.netbios.rcv_buf_size</code> (int, default 576)
     * 
     * @return netbios recieve buffer size
     */
    int getNetbiosRcvBufSize ();


    /**
     * 
     * Property <code>jcifs.netbios.retryTimeout</code> (int, default 3000)
     * 
     * @return timeout of retry requests, in milliseconds
     */
    int getNetbiosRetryTimeout ();


    /**
     * 
     * Property <code>jcifs.netbios.retryCount</code> (int, default 2)
     * 
     * @return maximum number of retries for netbios requests
     */
    int getNetbiosRetryCount ();


    /**
     * 
     * 
     * Property <code>jcifs.netbios.cachePolicy</code> in minutes (int, default 600)
     * 
     * @return netbios cache timeout, in seconds, 0 - disable caching, -1 - cache forever
     */
    int getNetbiosCachePolicy ();


    /**
     * 
     * @return the maximum size of IO buffers, limits the maximum message size
     */
    int getMaximumBufferSize ();


    /**
     * 
     * Property <code>jcifs.smb.client.transaction_buf_size</code> (int, default 65535)
     * 
     * @return maximum data size for SMB transactions
     */
    int getTransactionBufferSize ();


    /**
     * 
     * Property <code>jcifs.smb.maxBuffers</code> (int, default 16)
     * 
     * @return number of buffers to keep in cache
     */
    int getBufferCacheSize ();


    /**
     * 
     * Property <code>jcifs.smb.client.listCount</code> (int, default 200)
     * 
     * @return maxmimum number of elements to request in a list request
     */
    int getListCount ();


    /**
     * 
     * Property <code>jcifs.smb.client.listSize</code> (int, default 65435)
     * 
     * @return maximum data size for list/info requests (known overhead is subtracted)
     */
    int getListSize ();


    /**
     * 
     * 
     * Property <code>jcifs.smb.client.attrExpirationPeriod</code> (int, 5000)
     * 
     * @return timeout of file attribute cache
     */
    long getAttributeCacheTimeout ();


    /**
     * 
     * 
     * Property <code>jcifs.smb.client.ignoreCopyToException</code> (boolean, false)
     * 
     * @return whether to ignore exceptions that occur during file copy
     */
    boolean isIgnoreCopyToException ();


    /**
     * @param cmd
     * @return the batch limit for the given command
     */
    int getBatchLimit ( String cmd );


    /**
     * 
     * Property <code>jcifs.smb.client.notify_buf_size</code> (int, default 1024)
     * 
     * @return the size of the requested server notify buffer
     */
    int getNotifyBufferSize ();


    /**
     * 
     * 
     * Property <code>jcifs.smb.client.maxRequestRetries</code> (int, default 2)
     * 
     * @return retry SMB requests on failure up to n times
     */
    int getMaxRequestRetries ();


    /**
     * Property <code>jcifs.smb.client.strictResourceLifecycle</code> (bool, default false)
     * 
     * If enabled, SmbFile instances starting with their first use will hold a reference to their tree.
     * This means that trees/sessions/connections won't be idle-disconnected even if there are no other active
     * references (currently executing code, file descriptors).
     * 
     * Depending on the usage scenario, this may have some benefit as there won't be any delays for restablishing these
     * resources, however comes at the cost of having to properly release all SmbFile instances you no longer need.
     * 
     * @return whether to use strict resource lifecycle
     */
    boolean isStrictResourceLifecycle ();


    /**
     * This is solely intended for debugging
     * 
     * @return whether to track the locations from which resources were created
     */
    boolean isTraceResourceUsage ();


    /**
     * @param command
     * @return whether to allow creating compound requests with that command
     */
    boolean isAllowCompound ( String command );


    /**
     * Machine identifier
     * 
     * ClientGuid, ... are derived from this value.
     * 
     * Normally this should be randomly assigned for each client instance/configuration.
     * 
     * @return machine identifier (32 byte)
     */
    byte[] getMachineId ();


    /**
     * 
     * 
     * Property <code>jcifs.smb.client.disableSpnegoIntegrity</code> (boolean, false)
     * 
     * @return whether to disable sending/verifying SPNEGO mechanismListMIC
     */
    boolean isDisableSpnegoIntegrity ();


    /**
     * 
     * Property <code>jcifs.smb.client.enforceSpnegoIntegrity</code> (boolean, false)
     * 
     * @return whether to enforce verifying SPNEGO mechanismListMIC
     */
    boolean isEnforceSpnegoIntegrity ();


    /**
     * Property <code>jcifs.smb.client.SendNTLMTargetName</code> (boolean, true)
     * 
     * @return whether to send an AvTargetName with the NTLM exchange
     */
    boolean isSendNTLMTargetName ();


    /**
     * Property <code>jcifs.smb.client.guestPassword</code>, defaults to empty string
     * 
     * @return password used when guest authentication is requested
     */
    String getGuestPassword ();


    /**
     * Property <code>jcifs.smb.client.guestUsername</code>, defaults to GUEST
     * 
     * @return username used when guest authentication is requested
     */
    String getGuestUsername ();


    /**
     * Property <code>jcifs.smb.client.allowGuestFallback</code>, defaults to false
     * 
     * @return whether to permit guest logins when user authentication is requested
     */
    boolean isAllowGuestFallback ();
}
