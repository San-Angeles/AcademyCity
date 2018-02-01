
var Boots = {};
var mContext = com.mojang.minecraftpe.MainActivity.currentMainActivity.get();
var dexpath = "/storage/sdcard0/AppProjects/MCPE_PROJECT/AcademyCity/bin/classes.dex";

var loader = new Packages.dalvik.system.DexClassLoader(dexpath, mContext.getDir("dex", 0).getPath(), null, mContext.getClass().getClassLoader());
function build(name, dir) {
  Boots[name] = loader.loadClass(dir).newInstance();
  Boots[name].initialization(mContext, new java.lang.reflect.InvocationHandler({
		invoke:function(o, m, p) {
			switch(m.getName()) {
				case "equals":
					return false;
				case "hashCode":
					return 0;
				case "toString":
					return "function toString()\t{\t[native code, ailty=1]\t}";
				default:return eval(p[0]+"")
			}
		}
	}));
}

build("Launcher","com.sanangeles.academycity.Launcher");

function attackHook(attacker, victim) {
  Boots["Launcher"].attackHook(attacker, victim);
}
function chatHook(str) {
  Boots["Launcher"].chatHook(str);
}
function continueDestroyBlock(x, y, z, side, progress) {
  Boots["Launcher"].continueDestroyBlock(x, y, z, side, progress);
}
function destroyBlock(x, y, z, side) {
  Boots["Launcher"].destroyBlock(x, y, z, side);
}
function projectileHitEntityHook(projectile, targetEntity) {
  Boots["Launcher"].projectileHitEntityHook(projectile, targetEntity);
}
function eatHook(hearts, saturationRatio) {
  Boots["Launcher"].eatHook(hearts, saturationRatio);
}
function entityAddedHook(entity) {
  Boots["Launcher"].entityAddedHook(entity);
}
function entityHurtHook(attacker, victim, halfhearts) {
  Boots["Launcher"].entityHurtHook(attacker, victim, halfhearts);
}
function entityRemovedHook(entity) {
  Boots["Launcher"].entityRemovedHook(entity);
}
function explodeHook(entity, x, y, z, power, onFire) {
  Boots["Launcher"].explodeHook(entity, x, y, z, power, onFire);
}
function serverMessageReceiveHook(str) {
  Boots["Launcher"].serverMessageReceiveHook(str);
}
function deathHook(attacker, victim) {
  Boots["Launcher"].deathHook(attacker, victim);
}
function playerAddExpHook(player, experienceAdded) {
  Boots["Launcher"].playerAddExpHook(player, experienceAdded);
}
function playerExpLevelChangeHook(player, levelsAdded) {
  Boots["Launcher"].playerExpLevelChangeHook(player, levelsAdded);
}
function redstoneUpdateHook(x, y, z, newCurrent, someBooleanIDontKnow, blockId, blockData) {
  Boots["Launcher"].redstoneUpdateHook(x, y, z, newCurrent, someBooleanIDontKnow, blockId, blockData);
}
function screenChangeHook(screenName) {
  Boots["Launcher"].screenChangeHook(screenName);
}
function leaveGame() {
  Boots["Launcher"].leaveGame();
}
function newLevel() {
  Boots["Launcher"].newLevel();
}
function startDestroyBlock(x, y, z, side) {
  Boots["Launcher"].startDestroyBlock(x, y, z, side);
}
function projectileHitBlockHook(projectile, blockX, blockY, blockZ, side) {
  Boots["Launcher"].projectileHitBlockHook(projectile, blockX, blockY, blockZ, side);
}
function modTick() {
  Boots["Launcher"].modTick();
}
function useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage) {
  Boots["Launcher"].useItem(x, y, z, itemid, blockid, side, itemDamage, blockDamage);
}
