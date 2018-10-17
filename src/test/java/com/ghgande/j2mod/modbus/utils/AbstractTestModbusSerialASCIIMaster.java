/*
 * Copyright 2002-2016 jamod & j2mod development teams
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ghgande.j2mod.modbus.utils;

import com.ghgande.j2mod.modbus.Modbus;
import com.ghgande.j2mod.modbus.facade.ModbusSerialMaster;
import com.ghgande.j2mod.modbus.procimg.ProcessImage;
import com.ghgande.j2mod.modbus.slave.ModbusSerialSlave;
import com.ghgande.j2mod.modbus.slave.ModbusSlaveFactory;
import com.ghgande.j2mod.modbus.util.SerialParameters;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * All the master unit tests extend this class so that the system will automatically
 * create a test slave to work with and tear it down after a run
 *
 * @author Steve O'Hara (4NG)
 * @version 2.0 (March 2016)
 */
public class AbstractTestModbusSerialASCIIMaster extends AbstractTestModbus {

    protected static ModbusSerialMaster master;
    protected static ModbusSerialSlave slave;

    @Before
    public void windowsOnly() {
        org.junit.Assume.assumeTrue(isWindows());
    }

    @BeforeClass
    public static void setUpSlave() {
        try {
            slave = createSerialSlave(false);

            // Create master
            SerialParameters parameters = new SerialParameters();
            parameters.setPortName("CNCA0");
            parameters.setOpenDelay(1000);
            parameters.setEncoding(Modbus.SERIAL_ENCODING_ASCII);
            master = new ModbusSerialMaster(parameters);
            master.connect();
        } catch (Exception e) {
            tearDownSlave();
            fail(String.format("Cannot initialise tests - %s", e.getMessage()));
        }
    }

    @AfterClass
    public static void tearDownSlave() {
        if (master != null) {
            master.disconnect();
        }
        if (slave != null) {
            ModbusSlaveFactory.close(slave);
        }
        master = null;
        slave = null;
    }

    /**
     * Creates a Slave to use for testing
     *
     * @return Listener of the slave
     * @throws IOException If cannot connect to port
     */
    static ModbusSerialSlave createSerialSlave(boolean RTU) throws Exception {
        ModbusSerialSlave slave = null;
        try {
            // Create the test data
            ProcessImage spi = getSimpleProcessImage();

            // Create a serial slave
            SerialParameters parameters = new SerialParameters();
            parameters.setPortName("CNCB0");
            parameters.setOpenDelay(1000);
            parameters.setEncoding(RTU ? Modbus.SERIAL_ENCODING_RTU : Modbus.SERIAL_ENCODING_ASCII);
            slave = ModbusSlaveFactory.createSerialSlave(parameters);
            slave.addProcessImage(UNIT_ID, spi);

            // Open the slave
            slave.open();
        } catch (Exception x) {
            if (slave != null) {
                slave.close();
            }
            throw new Exception(x.getMessage());
        }
        return slave;
    }

}
